package uopeople.assignment.unit8.models.weatherstack;

public class Location {
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezone_id;
    private String localtime;
    private long localtime_epoch;
    private String utc_offset;

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return this.lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone_id() {
        return this.timezone_id;
    }

    public void setTimezone_id(String timezone_id) {
        this.timezone_id = timezone_id;
    }

    public String getLocaltime() {
        return this.localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public long getLocaltime_epoch() {
        return this.localtime_epoch;
    }

    public void setLocaltime_epoch(long localtime_epoch) {
        this.localtime_epoch = localtime_epoch;
    }

    public String getUtc_offset() {
        return this.utc_offset;
    }

    public void setUtc_offset(String utc_offset) {
        this.utc_offset = utc_offset;
    }
}
