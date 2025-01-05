package uopeople.assignment.unit8.models.weatherstack;

import uopeople.assignment.unit8.ApiConfiguration;

/**
 * The ApiEndpoints enum defines the endpoints available in the WeatherStack
 * API.
 * Each endpoint corresponds to a specific type of weather data request, such as
 * current weather, forecasts, or historical data.
 */
public enum ApiEndpoints {

    CURRENT("current"),
    FORECAST("forecast"),
    HISTORICAL("historical");

    private final String endpoint;

    /**
     * Constructor for ApiEndpoints enum.
     * 
     * @param endpoint the API endpoint path (e.g., "current", "forecast",
     *                 "historical").
     */
    ApiEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Constructs a URL for the API endpoint with the specified city.
     * 
     * @param city the name of the city to query.
     * @return the full API URL as a String.
     */
    public String withCity(String city) {
        return ApiConfiguration.buildUrl(this.endpoint, city);
    }

    /**
     * Constructs a URL for the API endpoint with the specified city and additional parameters.
     * 
     * @param city the name of the city to query.
     * @param additionalParams additional query parameters to append to the URL.
     * @return the full API URL as a String.
     */
    public String withAdditionalParameters(String city, String additionalParams) {
        return ApiConfiguration.buildUrl(this.endpoint, city, additionalParams);
    }
}
