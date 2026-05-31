package Structure;

import java.util.List;

/**
 * Represents the root structure of the dataset.
 * It combines the metadata and the list of individual stations into a single object,
 * which is primarily used for JSON serialization and deserialization.
 *
 * @param metadata The overall information about the dataset (e.g., timestamp, total count).
 * @param stations The list of all extracted river stations and their measurements.
 */
public record StructureCase(Metadata metadata, List<Station> stations){}