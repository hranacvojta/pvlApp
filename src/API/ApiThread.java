package API;

/**
 * A thread implementation responsible for asynchronously extracting data from the PVL portal.
 */
public class ApiThread implements Runnable {

    /**
     * A volatile flag indicating whether the API thread has finished its execution.
     */
    private static volatile boolean aipThreadDone = false;

    /**
     * Executes the thread, attempting to extract content from the target URL.
     * Updates the completion flag to true once the extraction is finished or if it fails.
     */
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

    /**
     * Checks if the API thread has completed its task.
     *
     * @return true if the thread is done, false otherwise.
     */
    public static boolean isAipThreadDone() {
        return aipThreadDone;
    }
}