// Created on 03.02.2021, 12:33

package de.malik.myapplication.util.customermanagement;

import androidx.annotation.NonNull;
import de.malik.myapplication.util.ConversionFactor;

public class Time {

    public static final Time EMPTY_TIME = new Time(0, 0);

    private int hours, minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public static int convert(@NonNull ConversionFactor conversion, Time time) {
        final int HOURS = time.getHours();
        final int MINUTES = time.getMinutes();
        final int TOTAL_MINUTES = (HOURS * 60) + MINUTES;
        final int TOTAL_SECONDS = TOTAL_MINUTES * 60;
        final int TOTAL_MILLIS = TOTAL_SECONDS * 1000;

        if (conversion == ConversionFactor.MINUTES) return TOTAL_MINUTES;
        else if (conversion == ConversionFactor.SECONDS) return TOTAL_SECONDS;
        else if (conversion == ConversionFactor.MILLIS) return TOTAL_MILLIS;
        return 0;
    }

    /**
     * creates a time string
     * @return a time string in the format: hour:minute for example 14:30
     */
    public String asString() {
        return hours + ":" + minutes;
    }

    /**
     * creates a formatted time string
     * @return a string in the format 'hours Std minutes Min'
     */
    public String asDiffString() {
        return hours + " Std " + minutes + " Min";
    }

    /**
     * creates a time string which is ready to be put into any kind of View
     * @return a time string in the format: 'hour:minute Uhr' for example '14:30 Uhr'
     */
    public String asString(String suffix) {
        String timeString = hours + ":" + minutes + " " + suffix;

        if (minutes < 10) {
            timeString = hours + ":0" + minutes + " " + suffix;
        }
        if (hours < 10) {
            timeString = "0" + timeString;
        }
        return timeString;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
