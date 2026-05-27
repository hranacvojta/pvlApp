import API.ConnetionManager.PvlConnector;
import API.ContentExtractor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Screens.TitleScreen ts = new Screens.TitleScreen();
//        ts.init();

        String goalAdress = "https://www.pvl.cz/portal/sap/cz/pc/";
        ContentExtractor.extractContent(goalAdress);
    }
}