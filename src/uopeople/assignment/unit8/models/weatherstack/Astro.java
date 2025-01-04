package uopeople.assignment.unit8.models.weatherstack;

public class Astro {
    
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moon_phase;
    private int moon_illumination;

    // Getters 
    public String getSunrise() {
        return this.sunrise;
    }

    public String getSunset() {
        return this.sunset;
    }

    public String getMoonrise() {
        return this.moonrise;
    }

    public String getMoonset() {
        return this.moonset;
    }

    public String getMoon_phase() {
        return this.moon_phase;
    }

    public int getMoon_illumination() {
        return this.moon_illumination;
    }

    // Setters

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public void setMoon_phase(String moon_phase) {
        this.moon_phase = moon_phase;
    }

    public void setMoon_illumination(int moon_illumination) {
        this.moon_illumination = moon_illumination;
    }

}
