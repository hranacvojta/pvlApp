package API;
import API.ConnetionManager.PvlConnector;
import Structure.Metadata;
import Structure.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Structure.StructureCase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ContentExtractor {


    public static void extractContent(String url) throws IOException {
//        System.out.println("start");
//        long startTime = System.nanoTime();
        Document doc = null;
        List<Station> stationList = new ArrayList<>();
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

        Elements hiddenInputs = doc.select("input[type=hidden]");

        for (Element input : hiddenInputs) {

            String value = input.attr("value");

            if (!value.contains("|")) {
                continue;
            }

            String[] parts = inputValueParser(input);

            if (parts.length < 7) {
                continue;
            }
            String river = parts[0].trim();
            String place = parts[1].trim();

            Integer height = securedParsingInt(parts[2]);
            Double flow_m3s = securedParsingDouble(parts[3]);
            Double waterTemperature_c = securedParsingDouble(parts[6]);


            int lastIndex = parts.length - 1;
            Integer spa = securedParsingInt(parts[lastIndex]);

            stationList.add(new Station(river, place, height, flow_m3s, waterTemperature_c, spa));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String cleanTime = LocalDateTime.now().format(formatter);

        //     TEST
//        System.out.println(PvlConnector.scrape(url));

        Metadata meta = new Metadata(cleanTime, stationList.size(), url);
        StructureCase structureCase = new StructureCase(meta, stationList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(structureCase);

        System.out.println("--- RESULTING JSON ---");
//        System.out.println(json);


        File file = new File("res/PvlStatistics.json");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println( e.getMessage());
        }
//        System.out.println("konec");
//        long endTime = System.nanoTime();
//        long duration = endTime - startTime;
//         duration = duration/1000000;
//        System.out.println(duration);
    }

    private static double securedParsingDouble(String text) {
        if (text == null || text.trim().isEmpty() || text.contains("-")) {
            return 0.0;
        }
        try {
            return Double.parseDouble(text.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }


    private static int securedParsingInt(String text) {
        if (text == null || text.trim().isEmpty() || text.contains("-")) {
            return 0;
        }
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String[] inputValueParser(Element input) {
        String origValue = input.attr("value");
        return origValue.split("\\|");
    }



}
