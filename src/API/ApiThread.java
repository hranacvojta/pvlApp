package API;

public class ApiThread implements Runnable {

    private static volatile boolean aipThreadDone = false;
    @Override
    public void run() {


        try {
            ContentExtractor.extractContent("https://www.pvl.cz/portal/sap/cz/pc/");

        } catch (Exception e) {
            System.err.println("[THREAD] An error occurred: " + e.getMessage());
        }

        System.out.println("successfully extracted content from web");

        aipThreadDone = true;
    }


    public static boolean isAipThreadDone() {
        return aipThreadDone;
    }
}
