package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class FridayFinder {
    private static final int FRIDAY_13TH = 13;

    private FridayFinder() {
    }

    public static List<LocalDate> findAllFridays13thInOneYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year should be above zero");
        }
        List<LocalDate> fridays = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(year, Month.JANUARY, 1);
        while (currentDate.getYear() == year) {
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (currentDate.getDayOfMonth() == FRIDAY_13TH) {
                fridays.add(currentDate);
            }
        }
        return fridays;
    }

    public static LocalDate findNextFriday13th(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date can't be null");
        }
        LocalDate nextFriday = date;
        while (true) {
            nextFriday = nextFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (nextFriday.getDayOfMonth() == FRIDAY_13TH) {
                return nextFriday;
            }
        }
    }
}
