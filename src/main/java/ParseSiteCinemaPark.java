import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseSiteCinemaPark {
    private static List <String> listRef = new ArrayList<>();

    /* Value keys in the Map films
    title
    duration
    ratings - not implemented
    genre
    director
    description
     */
    private static List <Map> wordsFilm = new ArrayList<>();

    public static List<Map> getPage() throws IOException {
        String url = "https://kinoteatr.ru/kinoafisha/saratov/";
        Document page = Jsoup.parse(new URL(url), 2000);
        Elements stickerFilms = page.select("a[class=movie_card_clickable_zone gtm-ec-list-item-movie]");

        listRef = stickerFilms.eachAttr("href");

        parsePageLink();

        return wordsFilm;
    }

    // The function needs to be rewritten to use DataBase(Hibernate)
    private static void parsePageLink() throws IOException{
        for(int i = 0; i < listRef.size(); i++) {
            Map<String, String> words = new HashMap();
            Document linkPage = Jsoup.parse(new URL(listRef.get(i)), 2000);
            Elements titleTag = linkPage.select("h1[itemprop=name]");
            Elements durationTag = linkPage.select("p[style=white-space: nowrap;]");
            Elements countryTag = linkPage.select("span[itemprop=countryOfOrigin]");
            Elements genreTag = linkPage.select("span[itemprop=genre]");
            Elements directorTag = linkPage.select("span[itemprop=director]");
            Elements descriptionTag = linkPage.select("p[itemprop=description]");
            words.put("title", titleTag.text());
            words.put("duration", durationTag.text());
            words.put("country", countryTag.text());
            words.put("genre", genreTag.text());
            words.put("director", directorTag.text());
            words.put("description", descriptionTag.text());
            words.put("link", listRef.get(i));

            wordsFilm.add(new HashMap(words));
        }
    }
}
