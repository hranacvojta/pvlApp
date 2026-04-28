import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {
    public static void main(String[] args) {
        TitleScreen ts = new TitleScreen();
        ts.init();
    }
}
//
//        // 1. Tohle je náš surový HTML kód (takhle nějak by vypadal stažený z internetu)
//        String htmlZWebu =
//                "<html>" +
//                        "  <body>" +
//                        "    <div class='produkt'>" +
//                        "      <h1 class='nazev'>Kniha: Java pro začátečníky</h1>" +
//                        "      <p>Popis: Nejlepší kniha na trhu.</p>" +
//                        "      <span class='cena'>599 Kč</span>" +
//                        "    </div>" +
//                        "  </body>" +
//                        "</html>";
//
//        // 2. Předáme to Jsoupu, ať to "přežvýká" do svého stromu (Document)
//        Document doc = Jsoup.parse(htmlZWebu);
//
//        // 3. DOLOVÁNÍ DAT:
//        // Řekneme: "Najdi prvek s třídou 'nazev' a dej mi z něj jen čistý text"
//        String vytezeneJmeno = doc.select(".nazev").text();
//
//        // Řekneme: "Najdi prvek s třídou 'cena' a dej mi z něj jen čistý text"
//        String vytezenaCena = doc.select(".cena").text();
//
//        // 4. Výpis do konzole
//        System.out.println("Úspěšně vykucháno z HTML!");
//        System.out.println("Název produktu: " + vytezeneJmeno);
//        System.out.println("Cena produktu: " + vytezenaCena);
//    }
//}



