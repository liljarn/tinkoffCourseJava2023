package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public final class FilterUtils {
    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;

    private FilterUtils() {
    }

    public static DirectoryStream.Filter<Path> filter = REGULAR_FILE
        .and(READABLE)
        .and(sizeFilter(100000L))
        .and(magicNumberFilter(0x89, 'P', 'N', 'G'))
        .and(extensionFilter(".png"))
        .and(regexNameFilter("_"));

    private static AbstractFilter sizeFilter(Long size) {
        return file -> Files.size(file) > size;
    }

    private static AbstractFilter extensionFilter(String extension) {
        return file -> {
            String fileName = file.getFileName().toString();
            int extensionIndex = fileName.indexOf(".");
            return fileName.substring(extensionIndex).equals(extension);
        };
    }

    private static AbstractFilter regexNameFilter(String regex) {
        return file -> {
            String fileName = file.getFileName().toString();
            Pattern filePattern = Pattern.compile(regex);
            return filePattern.matcher(fileName).find();
        };
    }

    private static AbstractFilter magicNumberFilter(int... magicNumbers) {
        return path -> {
            byte[] bytes = Files.readAllBytes(path);
            if (bytes.length < magicNumbers.length) {
                return false;
            }
            for (int j = 0; j < magicNumbers.length; j++) {
                if (bytes[j] != (byte) magicNumbers[j]) {
                    return false;
                }
            }
            return true;
        };
    }
}
