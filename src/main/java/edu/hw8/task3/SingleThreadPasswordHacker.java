package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;

public class SingleThreadPasswordHacker extends AbstractPasswordHacker {
    private final Map<String, String> passwords = new HashMap<>();

    private final Map<String, String> leakedPasswords;

    public SingleThreadPasswordHacker(Map<String, String> leakedPasswords) {
        this.leakedPasswords = leakedPasswords;
    }

    @Override
    public Map<String, String> hackPassword() {
        while (!leakedPasswords.isEmpty()) {
            for (int i = MIN_PASSWORD_LENGTH; i <= MAX_PASSWORD_LENGTH; i++) {
                nextPassword(i);
            }
        }
        return passwords;
    }

    private void nextPassword(int wordLength) {
        int[] index = new int[wordLength];
        while (!leakedPasswords.isEmpty()) {
            StringBuilder word = new StringBuilder();
            for (int j : index) {
                word.append(ALPHABET[j]);
            }

            String generatedPassword = word.toString();
            //LOGGER.info(generatedPassword);
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
        String md5Password = DigestUtils.md5Hex(generatedPassword);
        if (leakedPasswords.containsKey(md5Password)) {
            LOGGER.info("Password was founded");
            passwords.put(leakedPasswords.get(md5Password), generatedPassword);
            leakedPasswords.remove(md5Password);
            LOGGER.info(leakedPasswords.size());
        }
    }
}
