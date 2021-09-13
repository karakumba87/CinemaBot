import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ParseSiteCinemaPark {
    public ParseSiteCinemaPark() throws IOException{
        Document page = getPage();
        Elements stickerFilms = page.select("div[class=col-md-2 col-sm-6 col-xs-12 movie_card]");
        System.out.println(stickerFilms);
    }

    private static Document getPage() throws IOException {
        String url = "https://kinoteatr.ru/kinoafisha/saratov/";
        Document page = Jsoup.parse(new URL(url), 10000);
        return page;
    }
}
