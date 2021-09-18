import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ParseSiteCinemaPark.preLoad();

        TelegramBot bot = new TelegramBot();
        bot.setBotToken("");
        bot.setBotUsername("CheckCinemaSaratovBot");
        bot.botConnect();
    }
}
