package API.ConnetionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;


public class PvlConnector {

//    String url = "https://www.pvl.cz/portal/sap/cz/pc/";

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
