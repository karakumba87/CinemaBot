import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler {
    private static String helpMessage = "/help";
    private static String messageGetAll = "/all";
    private static String stopMessage = "/stop";

    public static SendMessage getRequest(Update update) {
        SendMessage message = new SendMessage();

        try {
            if (update.getMessage().getText().equals(messageGetAll)) {
                List<Map> data = new ArrayList<>();
                data.addAll(ParseSiteCinemaPark.getPage());
                message.setChatId(update.getMessage().getChatId().toString());

                for (int i = 0; i < data.size(); i++) {
                    Map<String, String> dataMap = new HashMap<>(data.get(i));
                    if(message.getText() != null)
                        message.setText(message.getText() + "\n" +
                                "<b>" + (i+1) + ". " + dataMap.get("title") + "</b>" +
                                "\n" + dataMap.get("genre") +
                                "\n" + dataMap.get("country"));
                    else
                        message.setText("<b>" + "1. " + dataMap.get("title") + "</b>" +
                                "\n" + dataMap.get("genre") +
                                "\n" + dataMap.get("country"));
                }
                message.setParseMode("HTML");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return message;
    }
}
