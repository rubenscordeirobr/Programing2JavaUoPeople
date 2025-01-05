package uopeople.assignment.unit8.models.weatherstack;

public class WeatherResponse {
    private Request request;
    private Location location;
    private Current current;
    private Forecast forecast;
    private Historical historical;

    // Getters and Setters
    public Request getRequest() {
        return this.request;
    }

    public Location getLocation() {
        return this.location;
    }

    
    public Current getCurrent() {
        return this.current;
    }

    public Forecast getForecast() {
        return this.forecast;
    }

    public Historical getHistorical() {
        return this.historical;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
 
    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public void setHistorical(Historical historical) {
        this.historical = historical;
    }
}
