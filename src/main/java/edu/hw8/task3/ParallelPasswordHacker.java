package edu.hw8.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.codec.digest.DigestUtils;

public class ParallelPasswordHacker extends AbstractPasswordHacker {
    private static final int WORDS_PER_THREAD = 3;
    private static final int THREADS = 8;

    private final ConcurrentMap<String, String> leakedPasswords;
    private final ConcurrentMap<String, String> hackedPasswords;
    private final ExecutorService executor;
    private final CountDownLatch latch;

    public ParallelPasswordHacker(Map<String, String> leakedPasswords) {
        this.leakedPasswords = new ConcurrentHashMap<>(leakedPasswords);
        this.hackedPasswords = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(THREADS);
        this.latch = new CountDownLatch(leakedPasswords.size());
    }

    @Override
    public Map<String, String> hackPassword() {
        for (int i = MIN_PASSWORD_LENGTH; i <= MAX_PASSWORD_LENGTH; i++) {
            int wordLength = i;
            for (int j = 0; j < ALPHABET.length; j += WORDS_PER_THREAD) {
                int startIndex = j;
                executor.execute(() -> nextPassword(wordLength, startIndex));
            }
        }
        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return hackedPasswords;
    }

    private void nextPassword(int wordLength, int startIndex) {
        int[] index = new int[wordLength];
        index[0] = startIndex;
        while (index[0] < startIndex + WORDS_PER_THREAD && !leakedPasswords.isEmpty()) {
            StringBuilder word = new StringBuilder();
            for (int j : index) {
                word.append(ALPHABET[j]);
            }

            String generatedPassword = word.toString();
            processGeneratedPassword(generatedPassword);

            for (int i = index.length - 1; ; --i) {
                if (i < 0) {
                    return;
                }
                index[i]++;
                if (index[i] == ALPHABET.length) {
                    index[i] = 0;
                } else {
                    break;
                }
            }
        }
    }

    private void processGeneratedPassword(String generatedPassword) {
        String md5Pass = DigestUtils.md5Hex(generatedPassword);
        if (leakedPasswords.containsKey(md5Pass)) {
            LOGGER.info("Password was founded");
            hackedPasswords.put(leakedPasswords.get(md5Pass), generatedPassword);
            leakedPasswords.remove(md5Pass);
            latch.countDown();
        }
    }
}
