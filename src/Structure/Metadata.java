package Structure;

/**
 * Represents the metadata associated with the scraped station data.
 *
 * @param actualTime       The timestamp indicating when the data was extracted or generated.
 * @param numberOfStations The total number of river stations included in the dataset.
 * @param source           The original URL or source from which the data was retrieved.
 */
public record Metadata(String actualTime, int numberOfStations, String source){}