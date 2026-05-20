import API.ContentExtractor;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static API.ConnetionManager.PvlConnector.scrape;

public class Main {
    public static void main(String[] args) throws IOException {
        Screens.TitleScreen ts = new Screens.TitleScreen();
        ts.init();
        Document document = scrape("https://www.pvl.cz/portal/sap/cz/pc/");
        ContentExtractor  contentExtractor = new ContentExtractor();
        contentExtractor.extractContent(document);

    }
}