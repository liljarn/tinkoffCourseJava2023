package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Client {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int SERVER_PORT = 9999;
    private static final int BUFFER_CAPACITY = 2048;

    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static Client instance;
    private final String name;

    public static Client start(String clientName) {
        if (instance == null) {
            instance = new Client(clientName);
        }
        return instance;
    }

    public static void stop() {
        try {
            client.close();
            buffer = null;
            LOGGER.info("Client disconnected");
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with disconnection");
        }
    }

    private Client(String clientName) {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", SERVER_PORT));
            buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            name = clientName;
            LOGGER.info("Client is connected!");
        } catch (IOException e) {
            throw new RuntimeException("Can't connect");
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava") public String sendMessage(String msg) {
        String response;
        try {
            if (msg.equals("stop")) {
                stop();
                return "";
            }
            client.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
            buffer.clear();
            client.read(buffer);
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            response = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Сервер:\n" + response);
            buffer.clear();
        } catch (IOException e) {
            throw new RuntimeException("Can't send message");
        }
        return response;
    }

    public String getName() {
        return name;
    }
}
