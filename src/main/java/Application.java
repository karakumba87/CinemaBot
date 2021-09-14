import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Application {
    public static void main(String[] args) throws IOException {
        //ParseSiteCinemaPark parcer = new ParseSiteCinemaPark();

        TelegramBot bot = new TelegramBot();
        bot.setBotToken("");
        bot.setBotUsername("");

        bot.botConnect();


    }
}
