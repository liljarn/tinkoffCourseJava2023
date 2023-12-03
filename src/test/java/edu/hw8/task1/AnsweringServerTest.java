package edu.hw8.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnsweringServerTest {
    private AnsweringServer server;

//    @Test
//    @DisplayName("Client stop test")
//    public void server_shouldHandleOtherClients_whenFirstClientsDisconnect()
//        throws ExecutionException, InterruptedException {
//        startServer();
//        ExecutorService clientThreads = Executors.newFixedThreadPool(3);
//        Future<Client>[] clients = new Future[3];
//        for (int i = 0; i < 3; i++) {
//            clients[i] = clientThreads.submit(() -> Client.start("Name"));
//            Thread.sleep(2000);
//        }
//        clients[0].get().sendMessage("stop");
//        clients[1].get().sendMessage("бананы");
//        String response = clients[2].get().sendMessage("борьба");
//        clientThreads.shutdown();
//        assertThat(response).isEqualTo(
//            "Никогда не сдавайся. Пока ты продолжаешь бороться, судьба всегда может наградить тебя.");
//
//    }

    @Test
    @DisplayName("Client and server basic test")
    public void run_shouldProcessRequestFromClient() {
        startServer();
        Client client = Client.start("Олег");
        String response = client.sendMessage("бананы");
        assertThat(response).isEqualTo(
            """
                — Шеф, ну что за бред? Нафига вообще отклеивать эти этикетки от бананов?
                — Для баланса Вселенной. Ведь где-то там, в Африке, такой же огузок, как ты, приклеивает эти этикетки.""");
        server.stop();
    }

    private void startServer() {
        server = new AnsweringServer();
        Thread serverThread = new Thread(server::start);
        serverThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }
}
