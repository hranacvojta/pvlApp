package API.ConnetionManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

/**
 * Utility class for establishing a connection to the Povodí Vltavy (PVL) website
 * and scraping its HTML content using Jsoup.
 */
public class PvlConnector {

    /**
     * The main URL of the PVL portal containing the station statistics.
     */
    static final String urlMain = "https://www.pvl.cz/portal/sap/cz/pc/";

    /**
     * Connects to the specified URL and retrieves the HTML document.
     * Uses a custom SSL configuration to bypass certificate validation and sets a 15-second timeout.
     *
     * @param url The target URL to scrape.
     * @return The parsed HTML {@link Document}, or {@code null} if the connection fails.
     * @throws IOException If a network error occurs (declared in signature).
     */
    public static Document scrape(String url) throws IOException {

        try{
            SSLSocketFactory ssl = SslConfigurator.sslFalseCertif();

            Document doc = Jsoup.connect(url)
                    .sslSocketFactory(ssl)
                    .timeout(15000)
                    .get();
            return doc;
        }catch (IOException e ) {
            System.err.println("Error connecting to " + url + ": " + e.getMessage());
        }
        return null;
    }
}