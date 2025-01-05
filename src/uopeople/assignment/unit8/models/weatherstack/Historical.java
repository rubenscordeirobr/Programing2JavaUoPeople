package uopeople.assignment.unit8.models.weatherstack;

import java.util.Map;

public class Historical {
     private Map<String, DailyForecast> dailyForecasts;

    // Getters and setters
    public Map<String, DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(Map<String, DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }
}
