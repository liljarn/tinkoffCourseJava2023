package edu.hw5.task3.parsers;

import java.time.LocalDate;
import java.util.Optional;

public class DateParserWithWords extends DateParserHelper {
    @Override
    public Optional<LocalDate> parse(String date) {
        switch (date) {
            case "yesterday" -> {
                return Optional.of(LocalDate.now().minusDays(1));
            }
            case "today" -> {
                return Optional.of(LocalDate.now());
            }
            case "tomorrow" -> {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            default -> {
                return parseNext(date);
            }
        }
    }
}
