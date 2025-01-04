package uopeople.assignment.unit8.services;

import java.util.Map;

import uopeople.assignment.unit8.utils.StringSimilarity;

public class WeatherIconManager {

    private static final Map<String, String> WEATHER_DESCRIPTION_TO_ICON = Map.ofEntries(
        Map.entry("Chance of Flurries", "chanceflurries.png"),
        Map.entry("Chance of Rain", "chancerain.png"),
        Map.entry("Chance of Sleet", "chancesleet.png"),
        Map.entry("Chance of Snow", "chancesnow.png"),
        Map.entry("Chance of Thunderstorms", "chancetstorms.png"),
        Map.entry("Clear", "clear.png"),
        Map.entry("Cloudy", "cloudy.png"),
        Map.entry("Overcast", "cloudy.png"),
        Map.entry("Flurries", "flurries.png"),
        Map.entry("Fog", "fog.png"),
        Map.entry("Hazy", "hazy.png"),
        Map.entry("Mostly Cloudy", "mostlycloudy.png"),
        Map.entry("Mostly Sunny", "mostlysunny.png"),
        Map.entry("Night - Chance of Flurries", "nt_chanceflurries.png"),
        Map.entry("Night - Chance of Rain", "nt_chancerain.png"),
        Map.entry("Night - Chance of Sleet", "nt_chancesleet.png"),
        Map.entry("Night - Chance of Snow", "nt_chancesnow.png"),
        Map.entry("Night - Chance of Thunderstorms", "nt_chancetstorms.png"),
        Map.entry("Night - Clear", "nt_clear.png"),
        Map.entry("Night - Cloudy", "nt_cloudy.png"),
        Map.entry("Night - Flurries", "nt_flurries.png"),
        Map.entry("Night - Fog", "nt_fog.png"),
        Map.entry("Night - Hazy", "nt_hazy.png"),
        Map.entry("Night - Mostly Cloudy", "nt_mostlycloudy.png"),
        Map.entry("Night - Mostly Sunny", "nt_mostlysunny.png"),
        Map.entry("Night - Partly Cloudy", "nt_partlycloudy.png"),
        Map.entry("Night - Partly Sunny", "nt_partlysunny.png"),
        Map.entry("Night - Rain", "nt_rain.png"),
        Map.entry("Night - Sleet", "nt_sleet.png"),
        Map.entry("Night - Snow", "nt_snow.png"),
        Map.entry("Night - Sunny", "nt_sunny.png"),
        Map.entry("Night - Thunderstorms", "nt_tstorms.png"),
        Map.entry("Night - Unknown", "nt_unknown.png"),
        Map.entry("Partly Cloudy", "partlycloudy.png"),
        Map.entry("Partly Sunny", "partlysunny.png"),
        Map.entry("Rain", "rain.png"),
        Map.entry("Sleet", "sleet.png"),
        Map.entry("Snow", "snow.png"),
        Map.entry("Sunny", "sunny.png"),
        Map.entry("Thunderstorms", "tstorms.png"),
        Map.entry("Unknown", "unknown.png")
);


    public static String getIconPath(String description) {
        String iconFileName = getFileName(description);
        return "/uopeople/assignment/unit8/resources/images/weather_icons/" + iconFileName;
    }

    private static String getFileName(String description) {

        if (WEATHER_DESCRIPTION_TO_ICON.containsKey(description)) {
            return WEATHER_DESCRIPTION_TO_ICON.get(description);
        }
        return tryToFindClosestMatch(description);
    }

    private static String tryToFindClosestMatch(String description) {

        String closestMatch = null;
        double highestSimilarity = 0.0;

        for (Map.Entry<String, String> entry : WEATHER_DESCRIPTION_TO_ICON.entrySet()) {

            double similarityScore = StringSimilarity.similarity(description, entry.getKey());
            if (similarityScore > highestSimilarity) {
                highestSimilarity = similarityScore;
                closestMatch = entry.getValue();
            }
        }

        if (highestSimilarity > 0.5) {
            return closestMatch;
        }
        return "unknown.png";
    }

}
