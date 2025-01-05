package uopeople.assignment.unit8.utils;

import uopeople.assignment.unit8.models.TemperatureUnit;

/**
 * The TemperatureUtil class provides utility methods for handling and
 * formatting temperature data.
 * It includes functionality to convert temperatures between Celsius and
 * Fahrenheit and format the result
 * for display with the appropriate unit symbol.
 * 
 */
public class TemperatureUtil {

    /**
     * Formats a temperature value according to the specified temperature unit
     * (Celsius or Fahrenheit).
     * Converts the temperature to Fahrenheit if the specified unit is Fahrenheit.
     * 
     * @param temperatureUnit the unit of temperature (Celsius or Fahrenheit).
     * @param temperature     the temperature value in Celsius.
     * @return a formatted temperature string with the unit symbol (e.g., "25 °C" or
     *         "77 °F").
     */
    public static String formatTemperature(TemperatureUnit temperatureUnit, int temperature) {

        if (temperatureUnit == TemperatureUnit.FAHRENHEIT) {
            // Convert to Fahrenheit
            temperature = (int) Math.round(temperature * 1.8 + 32);
        }
        return String.format("%d %s", temperature, temperatureUnit.getSymbol());
    }
}
