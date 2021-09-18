import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler {
    public void getRequest(Update update, TelegramBot bot) {
            if (update.getMessage().getText().equals(botCommantList.getAllFilms)) {
                getAllFilmsMessage(update, bot);
            }
            else if (update.getMessage().getText().equals(botCommantList.getStart) |
                    update.getMessage().getText().equals(botCommantList.getHelp)) {
                getStartAndHelpMessage(update, bot);
            }
            else if (Integer.valueOf(update.getMessage().getText()) > 0 &
                    Integer.valueOf(update.getMessage().getText()) <= ParseSiteCinemaPark.getListRef().size()) {
                getDescriptionOfFilm(update, bot);
            }
    }

    private void getStartAndHelpMessage(Update update, TelegramBot bot) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("<b>" + "Список команд:" + "</b>" + "\n" + "<b>" + "/start" + "</b>" + " и " +
                "<b>" + "/help" + "</b>" + " - выводят список доступных команд.\n" +
                "<b>" + "/all" + "</b>" + " - выводит список всех фильмов доступных сегодня в кинотеатре.\n" +
                "<b>" + "n" + "</b>" + " - где \"n\" - номер фильма. Выводит информацию о выбранном фильме.\n");
        message.setParseMode("HTML");
        bot.sendMessage(message);
    }

    private void getAllFilmsMessage(Update update, TelegramBot bot) {
        SendMessage message = new SendMessage();
        List<Map> data = new ArrayList<>();
        data.addAll(ParseSiteCinemaPark.getListRef());

        message.setChatId(update.getMessage().getChatId().toString());
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> dataMap = new HashMap<>(data.get(i));
            if(message.getText() != null) {
                message.setText(message.getText() + "\n" +
                        "<b>" + (i + 1) + ". " + dataMap.get("title") + "</b>" +
                        "\n" + dataMap.get("genre") +
                        "\n" + dataMap.get("country"));
            }
            else {
                message.setText("<b>" + "1. " + dataMap.get("title") + "</b>" +
                        "\n" + dataMap.get("genre") +
                        "\n" + dataMap.get("country"));
            }
        }
        message.setParseMode("HTML");
        bot.sendMessage(message);

    }

    private void getDescriptionOfFilm(Update update, TelegramBot bot) {
        SendMessage message = new SendMessage();
        List<Map> data = new ArrayList<>();
        data.addAll(ParseSiteCinemaPark.getListRef());
        Integer i = Integer.valueOf(update.getMessage().getText());
        Map<String, String> dataMap = new HashMap<>(data.get(i-1));

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(dataMap.get("link") + "\n" +
                "<b>" + dataMap.get("title") + "</b>" + "\n" +
                dataMap.get("duration") + "\n" +
                dataMap.get("country") + "\n" +
                dataMap.get("genre") + "\n" +
                dataMap.get("director") + "\n" +
                dataMap.get("description"));
        message.setParseMode("HTML");
        bot.sendMessage(message);
    }
}
