package uopeople.assignment.unit8.models.weatherstack;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class Forecast {

    private Map<String, DailyForecast> dailyForecasts = new HashMap<>();

    // Getter
    public Map<String, DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    // Setter for dynamic keys
    @JsonAnySetter
    public void setDynamicDailyForecast(String key, DailyForecast value) {
        dailyForecasts.put(key, value);
    }
}
