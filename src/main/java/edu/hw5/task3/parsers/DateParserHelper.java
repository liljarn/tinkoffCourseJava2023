package edu.hw5.task3.parsers;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParserHelper {
    private DateParserHelper nextParser;

    public static DateParserHelper link(DateParserHelper first, DateParserHelper... chain) {
        DateParserHelper head = first;
        for (DateParserHelper nextInChain: chain) {
            head.nextParser = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Optional<LocalDate> parse(String date);

    protected Optional<LocalDate> parseNext(String date) {
        if (nextParser == null) {
            return Optional.empty();
        }
        return nextParser.parse(date);
    }
}
