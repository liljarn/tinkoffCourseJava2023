package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ComputerClub {
    private static final Pattern SESSION_PATTERN =
        Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})$");
    private static final DateTimeFormatter SESSION_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private static final String SESSION_EXCEPTION_MESSAGE = "Sessions can't be null or empty";
    private static final String SESSION_FORMAT_MESSAGE = "Wrong session format";
    private static final String SESSION_BEGIN_MESSAGE = "Begin of the session can't be later than end of it";

    private ComputerClub() {
    }

    private static String convertDurationToHoursAndMinutes(Duration averageSession, int sessionsSize) {
        return Duration.ofSeconds(averageSession.getSeconds() / sessionsSize).toString().substring(2);
    }

    public static String countSession(List<String> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            throw new IllegalArgumentException(SESSION_EXCEPTION_MESSAGE);
        }
        Duration averageSession = Duration.ZERO;
        for (String session : sessions) {
            Matcher sessionMatcher = SESSION_PATTERN.matcher(session);
            if (!sessionMatcher.find()) {
                throw new IllegalArgumentException(SESSION_FORMAT_MESSAGE);
            }
            LocalDateTime startSession = LocalDateTime.parse(sessionMatcher.group(1), SESSION_FORMAT);
            LocalDateTime endSession = LocalDateTime.parse(sessionMatcher.group(2), SESSION_FORMAT);
            if (startSession.isAfter(endSession)) {
                throw new IllegalArgumentException(SESSION_BEGIN_MESSAGE);
            }
            averageSession = averageSession.plus(Duration.between(startSession, endSession));
        }
        return convertDurationToHoursAndMinutes(averageSession, sessions.size());
    }
}
