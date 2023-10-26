package edu.hw2.task3;

import edu.hw2.task3.connection_managers.ConnectionManager;
import edu.hw2.task3.connection_managers.DefaultConnectionManager;
import edu.hw2.task3.connection_managers.FaultyConnectionManager;
import edu.hw2.task3.connections.FaultyConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class ConnectionTest {
    @Test
    @DisplayName("Return FaultyConnection check")
    void faultyConnectionManager_shouldReturnFaultyConnection_whenCallMethodGetConnection() {
        assertThat(new FaultyConnectionManager().getConnection()).isInstanceOf(FaultyConnection.class);
    }

    static Stream<Arguments> remoteConnectionArguments() {
        return Stream.of(
            Arguments.of(new DefaultConnectionManager(), 5),
            Arguments.of(new FaultyConnectionManager(), 10)
        );
    }

    @DisplayName("Connecting to server test")
    @ParameterizedTest
    @MethodSource("remoteConnectionArguments")
    void remoteConnectionTest(ConnectionManager manager, int maxAttempts) {
        assertThatCode(() -> new PopularCommandExecutor(manager, maxAttempts).updatePackages()).doesNotThrowAnyException();
    }
}
