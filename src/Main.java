import API.ApiThread;
import API.ConnetionManager.PvlConnector;
import API.ContentExtractor;

import java.io.IOException;

/**
 * The main entry point for the application.
 * It handles the initialization of the background API thread for data extraction
 * and launches the initial graphical user interface.
 */
public class Main {

    /**
     * The main method that starts the application.
     * It spawns a new thread to fetch data from the web asynchronously
     * and simultaneously opens the Title Screen.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs during execution.
     */
    public static void main(String[] args) throws IOException {


        ApiThread apiThread = new ApiThread();
        Thread thread = new Thread(apiThread);
        thread.start();

        Screens.TitleScreen ts = new Screens.TitleScreen();
        ts.init();


    }
}