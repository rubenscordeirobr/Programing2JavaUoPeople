package uopeople.assignment.unit8.models;

/**
 * The TemperatureUnit enum represents the units of temperature that can be used
 * in the application. It provides a symbol for each unit that can be used to
 * display the temperature value.
 */
public enum TemperatureUnit {
    CELSIUS("°C"),
    FAHRENHEIT("°F");

    private final String symbol;

    /**
     * Creates a new TemperatureUnit with the specified symbol.
     * 
     * @param symbol the symbol representing the temperature unit.
     */
    TemperatureUnit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing the temperature unit.
     * 
     * @return the symbol representing the temperature unit.
     */
    public String getSymbol() {
        return this.symbol;
    }
}
