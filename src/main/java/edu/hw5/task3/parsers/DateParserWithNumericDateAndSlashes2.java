package edu.hw5.task3.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateParserWithNumericDateAndSlashes2 extends DateParserHelper {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yy");

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(LocalDate.parse(date, DATE_FORMATTER));
        } catch (DateTimeParseException e) {
            return parseNext(date);
        }
    }
}
