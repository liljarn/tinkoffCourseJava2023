package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnsweringServer {
    private static final int SERVER_PORT = 9999;
    private static final Logger LOGGER = LogManager.getLogger();
    private final Semaphore semaphore = new Semaphore(1, true);
    private final ExecutorService executorService = Executors.newFixedThreadPool(8);

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public void start() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(SERVER_PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            LOGGER.info("Server has been started");
            while (serverSocketChannel.isOpen()) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        if (semaphore.tryAcquire()) {
                            LOGGER.info("Accepting client connection");
                            register(selector, serverSocketChannel);
                            keyIterator.remove();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void stop() {
        try {
            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
            if (selector != null) {
                selector.close();
            }
            executorService.shutdown();
            LOGGER.info("Server has been stopped");
        } catch (IOException e) {
            LOGGER.error("Error while stopping the server: " + e.getMessage());
        }
    }

    private void register(Selector selector, ServerSocketChannel serverSocketChannel) {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_CONNECT);
            executorService.execute(() -> new Worker(client, semaphore).handleClient());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
