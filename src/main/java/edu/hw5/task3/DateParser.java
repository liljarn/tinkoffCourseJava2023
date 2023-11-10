package edu.hw5.task3;

import edu.hw5.task3.parsers.DateParserHelper;
import edu.hw5.task3.parsers.DateParserWithNumericDateAndDashes;
import edu.hw5.task3.parsers.DateParserWithNumericDateAndDashes2;
import edu.hw5.task3.parsers.DateParserWithNumericDateAndSlashes;
import edu.hw5.task3.parsers.DateParserWithNumericDateAndSlashes2;
import edu.hw5.task3.parsers.DateParserWithWordAgo;
import edu.hw5.task3.parsers.DateParserWithWords;
import java.time.LocalDate;
import java.util.Optional;

public final class DateParser {
    private DateParser() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date can't be empty or null");
        }
        DateParserHelper parser = DateParserHelper.link(
            new DateParserWithNumericDateAndDashes(),
            new DateParserWithNumericDateAndDashes2(),
            new DateParserWithNumericDateAndSlashes(),
            new DateParserWithNumericDateAndSlashes2(),
            new DateParserWithWords(),
            new DateParserWithWordAgo()
        );
        return parser.parse(date);
    }
}
