package uopeople.assignment.unit8;

/**
 * The ApiConfiguration class provides constants and utility methods for
 * constructing
 * API request URLs for the WeatherStack service.
 * 
 * <p>
 * It centralizes the API base URL and API key to simplify request building and
 * ensure consistent configuration across the application.
 * </p>
 */
public class ApiConfiguration {

    /**
     * The base URL of the WeatherStack API.
     */
    public static final String apiUrl = "http://api.weatherstack.com/";

    /**
     * The API key used for authenticating requests to the WeatherStack API.
     * The key is securely stored in the `SECRETS` class, which is not included
     * in the repository to ensure confidentiality and security. This approach
     * helps prevent unauthorized access and protects sensitive credentials.
     */
    public static final String apiKey = SECRETS.WEATHER_STACK_API_KEY;

    /**
     * Constructs the API URL for a specific endpoint and city.
     * 
     * @param endpoint the endpoint to query (e.g., "current", "forecast",
     *                 "historical")
     * @param city     the name of the city for the query
     * @return the constructed API URL as a String
     */
    public static String buildUrl(String endpoint, String city) {
        return apiUrl + endpoint + "?access_key=" + apiKey + "&query=" + city;
    }

    /**
     * Constructs the API URL for a specific endpoint, city, and additional query
     * parameters.
     * 
     * @param endpoint         the endpoint to query (e.g., "current", "forecast",
     *                         "historical")
     * @param city             the name of the city for the query
     * @param additionalParams additional query parameters to append to the URL
     * @return the constructed API URL as a String
     */
    public static String buildUrl(String endpoint, String city, String additionalParams) {
        return apiUrl + endpoint + "?access_key=" + apiKey + "&query=" + city + "&" + additionalParams;
    }

}
