package Structure;

/**
 * Represents a river station and its current hydro-meteorological measurements.
 *
 * @param river              The name of the river.
 * @param place              The specific location or name of the measuring station.
 * @param height_cm          The current water level height in centimeters.
 * @param flow_m3s           The current water flow rate in cubic meters per second (m³/s).
 * @param waterTemperature_c The current water temperature in degrees Celsius (°C).
 * @param floodActivityRate  The current flood activity level or rate (SPA).
 */
public record Station(String river, String place, int height_cm, double flow_m3s, double waterTemperature_c, int floodActivityRate){}