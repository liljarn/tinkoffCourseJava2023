package edu.hw5.task3.parsers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParserWithWordAgo extends DateParserHelper {
    private static final Pattern DATE_PATTERN = Pattern.compile("^([1-9]+) days ago$");

    @Override
    public Optional<LocalDate> parse(String date) {
        Matcher dateMatcher = DATE_PATTERN.matcher(date);
        if (date.equals("1 day ago")) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else if (dateMatcher.matches()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(dateMatcher.group(1))));
        } else {
            return parseNext(date);
        }
    }
}
