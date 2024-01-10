package edu.hw8.task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Worker {
    private static final int BUFFER_CAPACITY = 2048;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_OUTPUT =
        "Зачастую, только чувствуя приближение конца, мы можем что-то изменить.";
    private final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
    private final Map<String, String> mapOfResponses = Map.of(
        "бананы",
        "— Шеф, ну что за бред? Нафига вообще отклеивать эти этикетки от бананов?\n"
            + "— Для баланса Вселенной. Ведь где-то там, в Африке, такой же огузок, как ты, приклеивает эти этикетки.",
        "ошибки",
        "Мы не можем не совершать ошибки. Они — часть нашей жизни."
            + " А самое главное — они помогают нам найти правильный путь.",
        "борьба",
        "Никогда не сдавайся. Пока ты продолжаешь бороться, судьба всегда может наградить тебя.",
        "решения",
        "Порой нам нужно принять решение, от которого будет зависеть наша судьба..."
            + " И не только наша. Но иногда мы понимаем: чтобы сделать человека счастливым, правильнее будет его "
            + "отпустить, как бы тяжело это ни было... Да, так будет лучше... Наверное",
        "одиночество", "Солнце светит всем одинаково: будь то нерадивый отец или взбалмошная дочь..."
            + " Оно равно греет как влюбленным парам, так и одинокому парню, который уже забыл, что такое солнце"
    );

    private final SocketChannel client;
    private final Semaphore semaphore;

    public Worker(SocketChannel client, Semaphore semaphore) {
        this.client = client;
        this.semaphore = semaphore;
    }

    public void handleClient() {
        try (Selector selector = Selector.open()) {
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            LOGGER.info("Client tries to send message");
            while (client.isOpen()) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    if (key.isReadable()) {
                        buffer.clear();
                        int r = client.read(buffer);
                        if (r == -1) {
                            client.close();
                            selector.close();
                            LOGGER.info("Not accepting client messages anymore");
                            break;
                        }
                        LOGGER.info("Client message has been arrived!");
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String str = new String(bytes, StandardCharsets.UTF_8);
                        LOGGER.info(str);
                        ByteBuffer bufferResponse =
                                ByteBuffer.wrap(mapOfResponses.getOrDefault(str, DEFAULT_OUTPUT)
                                        .getBytes(StandardCharsets.UTF_8));
                        client.write(bufferResponse);
                        LOGGER.info("Server sent response to the client");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            semaphore.release();
            LOGGER.info("Semaphore has been released");
        }
    }
}
