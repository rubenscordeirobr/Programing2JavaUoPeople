package com.ecommerce.utils;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A utility class that provides formatting methods for money, percentages, and dates.
 * 
 * This class centralizes formatting logic for consistency throughout the application.
 */
public class FormatUtil {

    /**
     * Formats a monetary amount into a localized currency string.
     *
     * @param amount the monetary amount to format
     * @return the formatted currency string (e.g., "$1,200.00" in the US locale)
     */
    public static String formatMoney(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }

    /**
     * Formats a percentage value into a string with one decimal place followed by a percent symbol.
     *
     * @param percentage the percentage value to format (e.g., 50.5 for 50.5%)
     * @return the formatted percentage string (e.g., "50.5%")
     */
    public static String formatPercentage(double percentage) {
        return String.format("%.1f%%", percentage);
    }

    /**
     * Formats a {@link LocalDate} into a long date string, including the day of the week,
     * month name, day of the month, and year.
     *
     * @param date the {@link LocalDate} to format
     * @return the formatted long date string (e.g., "Monday, January 1, 2024")
     */
    public static String formatLongDate(LocalDate date) {
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
        return dayOfWeek + ", " + date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
    }
}
