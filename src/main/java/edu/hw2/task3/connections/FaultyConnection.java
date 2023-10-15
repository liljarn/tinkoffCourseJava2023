package edu.hw2.task3.connections;

import edu.hw2.task3.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static int FAULTY_CONNECTION_CHANCE = 5;
    final Random faultyConnectionRandom = new Random();
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        if ((faultyConnectionRandom.nextInt(FAULTY_CONNECTION_CHANCE) + 1) % FAULTY_CONNECTION_CHANCE == 0) {
            LOGGER.info(command + " has thrown an exception!");
            throw new ConnectionException();
        } else {
            LOGGER.info(command + " has successfully completed!");
        }
    }

    @Override
    public void close() throws Exception {
    }
}
