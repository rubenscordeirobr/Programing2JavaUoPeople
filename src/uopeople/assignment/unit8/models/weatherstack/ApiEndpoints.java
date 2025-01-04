package uopeople.assignment.unit8.models.weatherstack;

import uopeople.assignment.unit8.ApiConfiguration;

public enum ApiEndpoints {

    CURRENT("current"),
    FORECAST("forecast"),
    HISTORICAL("historical");

    private final String endpoint;

    ApiEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String withCity(String city) {
        return ApiConfiguration.buildUrl(this.endpoint, city);
    }

    public String withAdditionalParameters(String city, String additionalParams) {
        return ApiConfiguration.buildUrl(this.endpoint, city, additionalParams);
    }
}
