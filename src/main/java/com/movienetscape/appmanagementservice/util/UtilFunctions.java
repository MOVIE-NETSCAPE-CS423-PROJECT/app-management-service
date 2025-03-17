package com.movienetscape.appmanagementservice.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilFunctions {


    public static String getDurationInTimeFormat(Long totalDurationInSeconds) {

        long hours = totalDurationInSeconds / 3600;

        long minutes = (totalDurationInSeconds % 3600) / 60;

        long seconds = totalDurationInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);

    }

    public static LocalDate toDate(String dateString) {

        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }
}
