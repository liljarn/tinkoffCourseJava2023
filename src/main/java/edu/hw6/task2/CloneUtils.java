package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CloneUtils {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TXT = ".txt";

    private CloneUtils() {
    }

    public static void cloneFile(Path path) {
        int copyNumber = 1;
        Path copiedPath = generateUniqueCopyPath(path, copyNumber);

        while (Files.exists(copiedPath)) {
            copyNumber++;
            copiedPath = generateUniqueCopyPath(path, copyNumber);
        }

        try {
            Files.copy(path, copiedPath, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            LOGGER.info("Has been thrown IOException");
        }
    }

    private static Path generateUniqueCopyPath(Path path, int copyNumber) {
        String fileName = path.getFileName().toString().replace(TXT, "");
        String copySuffix = (copyNumber == 1) ? " — копия" : " — копия (" + copyNumber + ")";
        String newName = fileName + copySuffix + TXT;
        return path.resolveSibling(newName);
    }
}
