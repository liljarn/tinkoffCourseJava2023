package edu.hw9.task2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesTreeHandlerTest {
    @TempDir
    private static Path path;

    @BeforeAll
    @SneakyThrows
    public static void initPath() {
        resolveFiles(path.resolve("1"), ".txt", 15);
        resolveFiles(path.resolve("2"), ".zip", 7);
        resolveFiles(path.resolve("3"), ".tar", 3);
        resolveFiles(path.resolve("4"), ".trew", 1);
        resolveFiles(path.resolve("5"), ".fd", 2);
        resolveFiles(path.resolve("6"), ".exe", 11);
        resolveFiles(path.resolve("7"), ".docx", 5);
        Files.writeString(path.resolve("1").resolve("1.txt"), "test");
    }

    @SneakyThrows
    private static void resolveFiles(Path dir, String extension, int number) {
        Files.createDirectories(dir);
        for (int i = 0; i < number; i++) {
            Path filePath = dir.resolve(i + extension);
            Files.createFile(filePath);
        }
    }

    @Test
    @DisplayName("findDirectoriesWithMoreThanNumberFiles test")
    public void findDirectoriesWithMoreThanNumberFiles_shouldReturnListOfDirectoriesWithMoreThanNumberFiles() {
        List<Path> actual = FilesTreeHandler.findDirectoriesWithMoreThanNumberFiles(path, 3);
        List<String> directoryNames =
            actual.stream().map(el -> el.getFileName().toString())
                .toList();
        assertThat(directoryNames).containsExactlyInAnyOrder("1", "2", "6", "7");
    }

    @Test
    @DisplayName("filterFilesByPredicate with extension predicate test")
    public void filterFilesByPredicate_shouldReturnListOfFilesFilteredByExtensionPredicate() {
        List<Path> actual = FilesTreeHandler.filterFilesByPredicate(path, p -> p.toString().endsWith(".tar"));
        List<String> fileNames = actual.stream().map(el -> el.getFileName().toString())
            .toList();
        assertThat(fileNames).containsExactlyInAnyOrder("0.tar", "1.tar", "2.tar");
    }

    @Test
    @DisplayName("filterFilesByPredicate with file size predicate test")
    @SneakyThrows
    public void filterFilesByPredicate_shouldReturnListOfFilesFilteredByFileSizePredicate() {
        List<Path> actual = FilesTreeHandler.filterFilesByPredicate(path, p -> {
            try {
                return Files.size(p) > 0;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<String> fileNames = actual.stream().map(el -> el.getFileName().toString())
            .toList();
        assertThat(fileNames).containsExactlyInAnyOrder("1.txt");
    }
}
