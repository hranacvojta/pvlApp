import API.ApiThread;
import API.ConnetionManager.PvlConnector;
import API.ContentExtractor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        ApiThread apiThread = new ApiThread();
        Thread thread = new Thread(apiThread);
        thread.start();

        Screens.TitleScreen ts = new Screens.TitleScreen();
        ts.init();


    }
}