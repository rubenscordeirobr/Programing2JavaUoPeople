package uopeople.assignment.unit8;

import uopeople.assignment.unit8.models.TemperatureUnit;
import uopeople.assignment.unit8.models.weatherstack.HourlyForecast;
import uopeople.assignment.unit8.utils.*;
import uopeople.assignment.unit8.services.WeatherIconManager;

/**
 * The ForecastItem class represents a single weather forecast entry for a
 * specific hour.
 * It provides formatted details such as the time, temperature, weather
 * description,
 * and associated weather icon for UI display.
 * 
 */
public class ForecastItem {

    private int hour; // Hour of the forecast (24-hour format)
    private String formattedTime; // Formatted time for display
    private String temperature; // Formatted temperature with unit
    private String weatherDescription; // Description of the weather condition
    private String weatherIcon; // Path to the associated weather icon

    /**
     * Constructs a ForecastItem object based on an HourlyForecast and temperature
     * unit.
     * 
     * @param hourlyForecast  the HourlyForecast object containing raw forecast
     *                        data.
     * @param temperatureUnit the unit of temperature (e.g., Celsius or Fahrenheit).
     */
    public ForecastItem(HourlyForecast hourlyForecast, TemperatureUnit temperatureUnit) {

        try {

            // Parse and format the hour
            this.hour = Integer.parseInt(hourlyForecast.getTime()) / 100;

            // Format the time for display
            this.formattedTime = TimeUtil.formatTime(hourlyForecast.getTime());

            // Format the temperature based on the selected unit
            this.temperature = TemperatureUtil.formatTemperature(temperatureUnit, hourlyForecast.getTemperature());

            // Get the first weather description
            this.weatherDescription = hourlyForecast.getWeather_descriptions().get(0);

            // Retrieve the weather icon path for the description
            this.weatherIcon = WeatherIconManager.getIconPath(this.weatherDescription);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves the hour of the forecast.
     * 
     * @return the hour in 24-hour format.
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Retrieves the formatted time for display.
     * 
     * @return the formatted time as a String (e.g., "14:00").
     */
    public String getFormattedTime() {
        return this.formattedTime;
    }

    /**
     * Retrieves the formatted temperature for display.
     * 
     * @return the temperature as a String (e.g., "25°C").
     */
    public String getTemperature() {
        return this.temperature;
    }

    /**
     * Retrieves the weather description.
     * 
     * @return the weather description as a String (e.g., "Sunny").
     */
    public String getWeatherDescription() {
        return this.weatherDescription;
    }

    /**
     * Retrieves the path to the weather icon.
     * 
     * @return the weather icon file path as a String.
     */
    public String getWeatherIcon() {
        return this.weatherIcon;
    }

    /**
     * Provides a string representation of the ForecastItem for debugging or
     * logging.
     * 
     * @return a formatted string containing forecast details.
     */
    @Override
    public String toString() {
        return "ForecastItem{" +
                "hour='" + formattedTime + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                '}';
    }

}
