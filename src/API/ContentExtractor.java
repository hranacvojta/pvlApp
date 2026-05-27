package API;
import API.ConnetionManager.PvlConnector;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class ContentExtractor {


    public static void extractContent(String url) throws IOException {
        Document doc = null;

        Gson gson = new Gson();
        try {
            doc = PvlConnector.scrape(url);
        }catch (Exception e){
            System.err.println("Nepodařilo se spojit s webem: " + e.getMessage());
            return;
        }

        if (doc == null){
            System.out.println("Theres is no data(html) to process");
            return;
        }

        //     TEST
//        System.out.println(PvlConnector.scrape(url));



    }
}
