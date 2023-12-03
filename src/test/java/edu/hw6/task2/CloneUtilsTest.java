package edu.hw6.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw6.task2.CloneUtils.cloneFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CloneUtilsTest {
    private final Path filePath = Paths.get("src/main/resources/hw6/tinkoff big secret.txt");
    private final Path copyPath = Paths.get("src/main/resources/hw6/tinkoff big secret — копия.txt");
    private final Path secondCopyPath = Paths.get("src/main/resources/hw6/tinkoff big secret — копия (2).txt");

    @BeforeEach
    void deleteCopies() throws IOException {
        Files.deleteIfExists(copyPath);
        Files.deleteIfExists(secondCopyPath);
    }

    @Test
    @DisplayName("First copy existence test")
    public void cloneFiles_shouldCreateFile() {
        cloneFile(filePath);
        assertTrue(Files.exists(copyPath));
    }

    @Test
    @DisplayName("First created file is a copy test")
    public void cloneFiles_shouldCreateCopyOfFile() throws IOException {
        cloneFile(filePath);
        var lines = Files.readAllLines(filePath);
        var copyLines = Files.readAllLines(copyPath);
        assertEquals(lines, copyLines);
    }

    @Test
    @DisplayName("Second copy existence test")
    public void cloneFiles_shouldCreateSecondCopyOfFile() {
        cloneFile(filePath);
        cloneFile(filePath);
        assertTrue(Files.exists(secondCopyPath));
    }

    @Test
    @DisplayName("First copy existence when second copy exist test")
    public void cloneFiles_shouldCreateFirstCopyOfFile_whenSecondCopyExists() throws IOException {
        cloneFile(filePath);
        cloneFile(filePath);
        Files.delete(copyPath);
        cloneFile(filePath);
        assertTrue(Files.exists(copyPath));
    }
}
