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

public class WeatherService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService() {

    }

    public Current getWeatherCurrent(String city) {

        WeatherResponse response = this.get(ApiEndpoints.CURRENT, city, null).orElse(null);
        if (response == null) {
            return null;
        }
        return response.getCurrent();
    }

    public Forecast getWeatherForecast(String city) {
        String additionalParams = "forecast_days=1&hourly=1";
        WeatherResponse response = this.get(ApiEndpoints.FORECAST, city, additionalParams).orElse(null);
        if (response == null) {
            return null;
        }
        return response.getForecast();
    }

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
