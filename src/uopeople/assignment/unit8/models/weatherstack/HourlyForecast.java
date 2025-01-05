package uopeople.assignment.unit8.models.weatherstack;

import java.util.List;

public class HourlyForecast {
    
    private String time;
    private int temperature;
    private int wind_speed;
    private int wind_degree;
    private String wind_dir;
    private int weather_code;
    private List<String> weather_icons;
    private List<String> weather_descriptions;
    private int precip;
    private int humidity;
    private int visibility;
    private int pressure;
    private int cloudcover;
    private int heatindex;
    private int dewpoint;
    private int windchill;
    private int windgust;
    private int feelslike;
    private int chanceofrain;
    private int chanceofremdry;
    private int chanceofwindy;
    private int chanceofovercast;
    private int chanceofsunshine;
    private int chanceoffrost;
    private int chanceofhightemp;
    private int chanceoffog;
    private int chanceofsnow;
    private int chanceofthunder;
    private int uv_index;

    // Getters  
    public String getTime() {
        return this.time;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getWind_speed() {
        return this.wind_speed;
    }

    public int getWind_degree() {
        return this.wind_degree;
    }

    public String getWind_dir() {
        return this.wind_dir;
    }

    public int getWeather_code() {
        return this.weather_code;
    }

    public List<String> getWeather_icons() {
        return this.weather_icons;
    }

    public List<String> getWeather_descriptions() {
        return this.weather_descriptions;
    }

    public int getPrecip() {
        return this.precip;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public int getPressure() {
        return this.pressure;
    }

    public int getCloudcover() {
        return this.cloudcover;
    }

    public int getHeatindex() {
        return this.heatindex;
    }

    public int getDewpoint() {
        return this.dewpoint;
    }

    public int getWindchill() {
        return this.windchill;
    }

    public int getWindgust() {
        return this.windgust;
    }

    public int getFeelslike() {
        return this.feelslike;
    }

    public int getChanceofrain() {
        return this.chanceofrain;
    }

    public int getChanceofremdry() {
        return this.chanceofremdry;
    }

    public int getChanceofwindy() {
        return this.chanceofwindy;
    }

    public int getChanceofovercast() {
        return this.chanceofovercast;
    }

    public int getChanceofsunshine() {
        return this.chanceofsunshine;
    }

    public int getChanceoffrost() {
        return this.chanceoffrost;
    }

    public int getChanceofhightemp() {
        return this.chanceofhightemp;
    }

    public int getChanceoffog() {
        return this.chanceoffog;
    }

    public int getChanceofsnow() {
        return this.chanceofsnow;
    }

    public int getChanceofthunder() {
        return this.chanceofthunder;
    }

    public int getUv_index() {
        return this.uv_index;
    }

    // Setters
    public void setTime(String time) {
        this.time = time;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWind_speed(int wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_degree(int wind_degree) {
        this.wind_degree = wind_degree;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public void setWeather_code(int weather_code) {
        this.weather_code = weather_code;
    }

    public void setWeather_icons(List<String> weather_icons) {
        this.weather_icons = weather_icons;
    }

    public void setWeather_descriptions(List<String> weather_descriptions) {
        this.weather_descriptions = weather_descriptions;
    }

    public void setPrecip(int precip) {
        this.precip = precip;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setCloudcover(int cloudcover) {
        this.cloudcover = cloudcover;
    }

    public void setHeatindex(int heatindex) {
        this.heatindex = heatindex;
    }

    public void setDewpoint(int dewpoint) {
        this.dewpoint = dewpoint;
    }

    public void setWindchill(int windchill) {
        this.windchill = windchill;
    }

    public void setWindgust(int windgust) {
        this.windgust = windgust;
    }

    public void setFeelslike(int feelslike) {
        this.feelslike = feelslike;
    }

    public void setChanceofrain(int chanceofrain) {
        this.chanceofrain = chanceofrain;
    }

    public void setChanceofremdry(int chanceofremdry) {
        this.chanceofremdry = chanceofremdry;
    }

    public void setChanceofwindy(int chanceofwindy) {
        this.chanceofwindy = chanceofwindy;
    }

    public void setChanceofovercast(int chanceofovercast) {
        this.chanceofovercast = chanceofovercast;
    }

    public void setChanceofsunshine(int chanceofsunshine) {
        this.chanceofsunshine = chanceofsunshine;
    }

    public void setChanceoffrost(int chanceoffrost) {
        this.chanceoffrost = chanceoffrost;
    }

    public void setChanceofhightemp(int chanceofhightemp) {
        this.chanceofhightemp = chanceofhightemp;
    }

    public void setChanceoffog(int chanceoffog) {
        this.chanceoffog = chanceoffog;
    }

    public void setChanceofsnow(int chanceofsnow) {
        this.chanceofsnow = chanceofsnow;
    }

    public void setChanceofthunder(int chanceofthunder) {
        this.chanceofthunder = chanceofthunder;
    }

    public void setUv_index(int uv_index) {
        this.uv_index = uv_index;
    }
}
