package uopeople.assignment.unit8.services;

import uopeople.assignment.unit8.models.weatherstack.*;
import uopeople.assignment.unit8.utils.DebugUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;

/**
 * The WeatherService class provides methods for retrieving real-time weather
 * data,
 * including current weather, forecasts, and historical data. It integrates with
 * the WeatherStack API to fetch and parse the data. In debug mode, it uses
 * local
 * mock data to avoid redundant API calls.
 */
public class WeatherService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Default constructor for WeatherService.
     * Initializes the ObjectMapper for JSON parsing.
     */
    public WeatherService() {

    }

    /**
     * Retrieves the current weather data for a specified city.
     * 
     * @param city the name of the city to retrieve weather data for
     * @return a Current object containing current weather details, or null if the
     *         API call fails
     */
    public Current getWeatherCurrent(String city) {

        WeatherResponse response = this.get(ApiEndpoints.CURRENT, city, null).orElse(null);
        if (response == null) {
            return null;
        }
        return response.getCurrent();
    }

    /**
     * Retrieves a short-term weather forecast for a specified city.
     * 
     * @param city the name of the city to retrieve the forecast for
     * @return a Forecast object containing forecast details, or null if the API
     *         call fails
     */
    public Forecast getWeatherForecast(String city) {
        String additionalParams = "forecast_days=1&hourly=1";
        WeatherResponse response = this.get(ApiEndpoints.FORECAST, city, additionalParams).orElse(null);
        if (response == null) {
            return null;
        }
        return response.getForecast();
    }

    /**
     * Retrieves historical weather data for a specified city and date.
     * 
     * @param city the name of the city
     * @param date the LocalDate for which to retrieve historical data
     * @return a Historical object containing historical weather details, or null if
     *         the API call fails
     */
    public Historical getWeatherHistorical(String city, LocalDate date) {
        // Format date to "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        String additionalParams = "historical_date=" + formattedDate;
        WeatherResponse response = this.get(ApiEndpoints.HISTORICAL, city, additionalParams).orElse(null);
        if (response == null) {
            return null;
        }
        return response.getHistorical();
    }

    /**
     * Helper method to retrieve weather data from the API.
     * 
     * @param endpoint         the API endpoint (e.g., CURRENT, FORECAST,
     *                         HISTORICAL)
     * @param city             the name of the city
     * @param additionalParams optional query parameters for the API request
     * @return an Optional<WeatherResponse> containing the parsed response, or empty
     *         if an error occurs
     */
    private Optional<WeatherResponse> get(ApiEndpoints endpoint, String city, String additionalParams) {
        try {

            String response = this.getResponse(endpoint, city, additionalParams);
            WeatherResponse weatherResponse = this.parseWeatherResponse(response, endpoint);

            if (weatherResponse != null) {

                return Optional.of(weatherResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    /**
     * Makes an HTTP request to the API or retrieves mock data in debug mode.
     * 
     * @param endpoint         the API endpoint
     * @param city             the name of the city
     * @param additionalParams optional query parameters
     * @return the raw response body as a String
     * @throws RuntimeException if the request fails
     */
    private String getResponse(ApiEndpoints endpoint, String city, String additionalParams) {

        try {

            if (DebugUtil.isDebuggerAttached()) {

                if (endpoint == ApiEndpoints.CURRENT) {
                    // if debug read data from file avoiding multiple requests
                    Path fullPath = Paths.get("src/uopeople/assignment/unit8/resources/data/weather_response.json");
                    return new String(Files.readAllBytes(fullPath));
                }

                if (endpoint == ApiEndpoints.FORECAST) {

                    // if debug read data from file avoiding multiple requests
                    Path fullPath = Paths.get("src/uopeople/assignment/unit8/resources/data/fake_forecast.json");
                    return new String(Files.readAllBytes(fullPath));
                }

            }

            String url = (additionalParams == null || additionalParams.isBlank())
                    ? endpoint.withCity(city)
                    : endpoint.withAdditionalParameters(city, additionalParams);

            // Create a new HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create a new HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get weather response", e);
        }
    }

    /**
     * Parses the JSON response from the API into a WeatherResponse object.
     * Handles API errors and substitutes mock data if necessary.
     * 
     * @param responseBody the raw JSON response
     * @param endpoint     the API endpoint
     * @return a WeatherResponse object or null if parsing fails
     * @throws RuntimeException if parsing fails or the API returns an error
     */
    private WeatherResponse parseWeatherResponse(String responseBody, ApiEndpoints endpoint) {

        try {
            // First, check if the response matches ApiResponseError
            ApiResponseError apiError = objectMapper.readValue(responseBody, ApiResponseError.class);

            if (!apiError.isSuccess()) {

                if (apiError.getError().getCode() == 603 || apiError.getError().getCode() == 609) {

                    // that means the my subscription plan current subscription plan does not
                    // support weather forecast or historical
                    // so we will use fake data for the purpose of this assignment

                    if (endpoint == ApiEndpoints.FORECAST) {

                        Path fullPath = Paths.get("src/uopeople/assignment/unit8/resources/data/fake_forecast.json");
                        return objectMapper.readValue(new String(Files.readAllBytes(fullPath)), WeatherResponse.class);
                    }

                }
                throw new RuntimeException("API Error: " + apiError.getError().getInfo());
            }

        } catch (Exception ignored) {
            // Ignored because the response might not be an ApiResponseError
        }

        try {
            // Deserialize JSON string to WeatherResponse object

            return objectMapper.readValue(responseBody, WeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse weather response", e);
        }

    }
}
