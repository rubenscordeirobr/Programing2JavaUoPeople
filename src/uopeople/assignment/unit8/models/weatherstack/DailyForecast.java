package uopeople.assignment.unit8.models.weatherstack;

import java.util.List;

public class DailyForecast {

    private String date;
    private long date_epoch;
    private Astro astro;
    private int mintemp;
    private int maxtemp;
    private int avgtemp;
    private int totalsnow;
    private double sunhour;
    private int uv_index;
    private List<HourlyForecast> hourly;

    // Getters  

    public String getDate() {
        return this.date;
    }

    public long getDate_epoch() {
        return this.date_epoch;
    }

    public Astro getAstro() {
        return this.astro;
    }

    public int getMintemp() {
        return this.mintemp;
    }

    public int getMaxtemp() {
        return this.maxtemp;
    }

    public int getAvgtemp() {
        return this.avgtemp;
    }

    public int getTotalsnow() {
        return this.totalsnow;
    }

    public double getSunhour() {
        return this.sunhour;
    }

    public int getUv_index() {
        return this.uv_index;
    }

    public List<HourlyForecast> getHourly() {
        return this.hourly;
    }

    // Setters

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate_epoch(long date_epoch) {
        this.date_epoch = date_epoch;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public void setMintemp(int mintemp) {
        this.mintemp = mintemp;
    }

    public void setMaxtemp(int maxtemp) {
        this.maxtemp = maxtemp;
    }

    public void setAvgtemp(int avgtemp) {
        this.avgtemp = avgtemp;
    }

    public void setTotalsnow(int totalsnow) {
        this.totalsnow = totalsnow;
    }

    public void setSunhour(double sunhour) {
        this.sunhour = sunhour;
    }

    public void setUv_index(int uv_index) {
        this.uv_index = uv_index;
    }

    public void setHourly(List<HourlyForecast> hourly) {
        this.hourly = hourly;
    }

    
}
