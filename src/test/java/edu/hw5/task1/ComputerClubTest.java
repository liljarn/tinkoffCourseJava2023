package edu.hw5.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw5.task1.ComputerClub.countSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ComputerClubTest {
    private static Stream<Arguments> sessionsForTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                "3H40M"
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 21:20",
                    "2022-04-01, 21:30 - 2022-04-01, 22:30"
                ),
                "1H"
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 20:40",
                    "2022-04-01, 21:30 - 2022-04-01, 22:10"
                ),
                "30M"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("sessionsForTest")
    @DisplayName("Correct session input test")
    public void countSession_shouldReturnCorrectTime_whenCorrectSessionInput(
        List<String> sessions,
        String sessionTime
    ) {
        assertThat(countSession(sessions)).isEqualTo(sessionTime);
    }

    @Test
    @DisplayName("Session has wrong format test")
    public void countSession_shouldThrowException_whenSessionHasWrongFormat() {
        List<String> sessions = List.of(
            "2022/03/12, 20:20 - 2022/03/12, 23:50"
        );
        assertThatThrownBy(() -> countSession(sessions)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Start of session later then end of it test")
    public void countSession_shouldThrowException_whenStartOfSessionIsLaterThanEndOfIt() {
        List<String> sessions = List.of(
            "2022-03-12, 23:50 - 2022-03-12, 20:20"
        );
        assertThatThrownBy(() -> countSession(sessions)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty sessions list test")
    public void countSession_shouldThrowException_whenListIsNullOrEmpty(List<String> sessions) {
        assertThatThrownBy(() -> countSession(sessions)).isInstanceOf(IllegalArgumentException.class);
    }
}
