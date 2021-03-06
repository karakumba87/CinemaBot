import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler {
    private ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
    private SendMessage message = new SendMessage();
    private ArrayList<KeyboardRow> key = new ArrayList<>();
    private KeyboardRow firstKeyRow = new KeyboardRow();
    private KeyboardRow secondKeyRow = new KeyboardRow();
    private KeyboardRow thirdKeyRow = new KeyboardRow();
    private KeyboardRow fourthKeyRow = new KeyboardRow();
    private KeyboardRow fifthKeyRow = new KeyboardRow();
    private KeyboardRow sixthKeyRow = new KeyboardRow();
    private KeyboardRow seventhKeyRow = new KeyboardRow();

    public void getRequest(Update update, TelegramBot bot) {
        message.setChatId(update.getMessage().getChatId().toString());
        message.setReplyMarkup(keyboard);
        message.setParseMode("HTML");
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        key.clear();
        firstKeyRow.clear();
        secondKeyRow.clear();
        thirdKeyRow.clear();
        fourthKeyRow.clear();
        fifthKeyRow.clear();
        sixthKeyRow.clear();
        seventhKeyRow.clear();

        if (update.getMessage().getText().equals(botCommandList.getAllFilms)) {
            getAllFilmsMessage(bot);
        } else if (update.getMessage().getText().equals(botCommandList.getBack)) {
            getBack(bot);
        } else if (update.getMessage().getText().equals(botCommandList.getStart) |
                update.getMessage().getText().equals(botCommandList.getHelp)) {
            getStartAndHelpMessage(bot);
        } else if (update.getMessage().getText().equals(botCommandList.getGenre)) {
            getGenre(bot);
        } else if (botCommandList.genreWords.contains(update.getMessage().getText())) {
            sortedByGenre(update, bot);
        } else if (Integer.valueOf(update.getMessage().getText()) > 0 &
                Integer.valueOf(update.getMessage().getText()) <= ParseSiteCinemaPark.getListRef().size()) {
            getDescriptionOfFilm(update, bot);
        }
    }

    private void getStartAndHelpMessage(TelegramBot bot) {
        firstKeyRow.add("?????? ????????????");
        firstKeyRow.add("??????????");
        secondKeyRow.add("??????????");
        secondKeyRow.add("????????????");
        key.add(firstKeyRow);
        key.add(secondKeyRow);
        keyboard.setKeyboard(key);

        message.setText("<b>" + "???????????? ????????????:" + "</b>" + "\n" + "<b>" + "/start" + "</b>" + " ?? " +
                "<b>" + "/help" + "</b>" + " - ?????????????? ???????????? ?????????????????? ????????????.\n" +
                "<b>" + "/all" + "</b>" + " - ?????????????? ???????????? ???????? ?????????????? ?????????????????? ?????????????? ?? ????????????????????.\n" +
                "<b>" + "n" + "</b>" + " - ?????? \"n\" - ?????????? ????????????. ?????????????? ???????????????????? ?? ?????????????????? ????????????.\n");
        bot.sendMessage(message);
    }

    private void getAllFilmsMessage(TelegramBot bot) {
        firstKeyRow.add("?????? ????????????");
        firstKeyRow.add("??????????");
        secondKeyRow.add("??????????");
        secondKeyRow.add("????????????");
        key.add(firstKeyRow);
        key.add(secondKeyRow);
        keyboard.setKeyboard(key);

        List<Map> data = new ArrayList<>();
        data.addAll(ParseSiteCinemaPark.getListRef());

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> dataMap = new HashMap<>(data.get(i));
            if (message.getText() != null) {
                message.setText(message.getText() + "\n" +
                        "<b>" + (i + 1) + ". " + dataMap.get("title") + "</b>" +
                        "\n" + dataMap.get("rate") +
                        "\n" + dataMap.get("genre") +
                        "\n" + dataMap.get("country"));
            } else {
                message.setText("<b>" + "1. " + dataMap.get("title") + "</b>" +
                        "\n" + dataMap.get("rate") +
                        "\n" + dataMap.get("genre") +
                        "\n" + dataMap.get("country"));
            }
        }
        bot.sendMessage(message);

    }

    private void getDescriptionOfFilm(Update update, TelegramBot bot) {
        firstKeyRow.add("?????? ????????????");
        firstKeyRow.add("??????????");
        secondKeyRow.add("??????????");
        secondKeyRow.add("????????????");
        key.add(firstKeyRow);
        key.add(secondKeyRow);
        keyboard.setKeyboard(key);

        List<Map> data = new ArrayList<>();
        data.addAll(ParseSiteCinemaPark.getListRef());
        Integer i = Integer.valueOf(update.getMessage().getText());
        Map<String, String> dataMap = new HashMap<>(data.get(i - 1));

        message.setText(dataMap.get("link") + "\n" +
                "<b>" + dataMap.get("title") + "</b>" + "\n" +
                dataMap.get("rate") + "\n" +
                dataMap.get("duration") + "\n" +
                dataMap.get("country") + "\n" +
                dataMap.get("genre") + "\n" +
                dataMap.get("director") + "\n" +
                dataMap.get("description"));
        bot.sendMessage(message);
    }

    private void getGenre(TelegramBot bot) {
        firstKeyRow.add("??????????");
        firstKeyRow.add("??????????????????????");
        firstKeyRow.add("????????????????????");
        secondKeyRow.add("????????????????");
        secondKeyRow.add("??????????????");
        secondKeyRow.add("????????????????");
        thirdKeyRow.add("??????????");
        thirdKeyRow.add("??????????");
        thirdKeyRow.add("????????????????");
        fourthKeyRow.add("??????????????");
        fourthKeyRow.add("??????????");
        fourthKeyRow.add("????????????????????");
        fifthKeyRow.add("??????????????");
        fifthKeyRow.add("????????????");
        fifthKeyRow.add("????????????????");
        sixthKeyRow.add("??????????????????");
        seventhKeyRow.add("??????????");
        seventhKeyRow.add("????????????");
        key.add(firstKeyRow);
        key.add(secondKeyRow);
        key.add(thirdKeyRow);
        key.add(fourthKeyRow);
        key.add(fifthKeyRow);
        key.add(sixthKeyRow);
        key.add(seventhKeyRow);
        keyboard.setKeyboard(key);

        message.setText("???????????????? ???????? ???? ????????????:");
        bot.sendMessage(message);
    }

    private void getBack(TelegramBot bot) {
        firstKeyRow.add("?????? ????????????");
        firstKeyRow.add("??????????");
        secondKeyRow.add("??????????");
        secondKeyRow.add("????????????");
        key.add(firstKeyRow);
        key.add(secondKeyRow);
        keyboard.setKeyboard(key);

        message.setText("?????????????????? ??????????????::");
        bot.sendMessage(message);
    }

    private void sortedByGenre(Update update, TelegramBot bot) {
        firstKeyRow.add("?????? ????????????");
        firstKeyRow.add("??????????");
        secondKeyRow.add("??????????");
        secondKeyRow.add("????????????");
        key.add(firstKeyRow);
        key.add(secondKeyRow);
        keyboard.setKeyboard(key);

        String customGenre = update.getMessage().getText();

        List<Map> data = new ArrayList<>();
        data.addAll(ParseSiteCinemaPark.getListRef());

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> dataMap = new HashMap<>(data.get(i));

            if (dataMap.get("genre").contains(customGenre) & message.getText() != null) {
                message.setText(message.getText() + "\n" +
                        "<b>" + (i + 1) + ". " + dataMap.get("title") + "</b>" +
                        "\n" + dataMap.get("rate") +
                        "\n" + dataMap.get("genre") +
                        "\n" + dataMap.get("country"));
            } else if (dataMap.get("genre").contains(customGenre)) {
                message.setText("<b>" + "1. " + dataMap.get("title") + "</b>" +
                        "\n" + dataMap.get("rate") +
                        "\n" + dataMap.get("genre") +
                        "\n" + dataMap.get("country"));
            }
        }
        bot.sendMessage(message);
    }
}
