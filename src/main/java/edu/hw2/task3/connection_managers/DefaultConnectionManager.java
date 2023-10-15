package edu.hw2.task3.connection_managers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final static int FAULTY_CONNECTION_CHANCE = 5;
    final Random faultyConnectionRandom = new Random();

    @Override
    public Connection getConnection() {
        if ((faultyConnectionRandom.nextInt(FAULTY_CONNECTION_CHANCE) + 1) % FAULTY_CONNECTION_CHANCE == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
