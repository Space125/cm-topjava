package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

//    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
//        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
//    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen( T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) < 0;
    }

    public static LocalDateTime atStartOfDayMin(LocalDate ld){
        return ld == null ? LocalDateTime.MIN : ld.atStartOfDay();
    }

    public static LocalDateTime atStartOfNextDayMax(LocalDate ld){
        return ld == null ? LocalDateTime.MAX : ld.plus(1, ChronoUnit.DAYS).atStartOfDay();
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

