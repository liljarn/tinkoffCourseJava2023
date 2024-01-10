package edu.hw8.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class ParallelPasswordHackerTest {
    @Test
    @DisplayName("hackPassword parallel test")
    public void parallelHackPassword_shouldReturnCorrectMapOfUsersAndPasswords() {
        Map<String, String> leakedPasswords = new HashMap<>();
        leakedPasswords.put("93f725a07423fe1c889f448b33d21f46", "Ivan");
        leakedPasswords.put("ef0cc10ff462af3af3e15f592c4ec8da", "Anton");
        leakedPasswords.put("65fa18d1e48eb9bf8844df126d662fc0", "Alexey");
        leakedPasswords.put("a1e05ee2564dbf16b04f09a23d482d06", "Yan");
        Map<String, String> passwords = new HashMap<>();
        passwords.put("Ivan", "java");
        passwords.put("Anton", "tokyo");
        passwords.put("Alexey", "ghoul");
        passwords.put("Yan", "cola");
        assertThat(new ParallelPasswordHacker(leakedPasswords).hackPassword()).containsExactlyInAnyOrderEntriesOf(
            passwords);
    }
}
