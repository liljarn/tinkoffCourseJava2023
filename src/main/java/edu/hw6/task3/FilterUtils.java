package edu.hw6.task3;

import java.nio.file.Files;
import java.util.regex.Pattern;

public final class FilterUtils {
    private FilterUtils() {
    }

    public static AbstractFilter sizeFilter(Long size) {
        return file -> Files.size(file) > size;
    }

    public static AbstractFilter extensionFilter(String extension) {
        return file -> {
            String fileName = file.getFileName().toString();
            int extensionIndex = fileName.indexOf(".");
            return fileName.substring(extensionIndex).equals(extension);
        };
    }

    public static AbstractFilter regexNameFilter(String regex) {
        return file -> {
            String fileName = file.getFileName().toString();
            Pattern filePattern = Pattern.compile(regex);
            return filePattern.matcher(fileName).find();
        };
    }

    public static AbstractFilter magicNumberFilter(int... magicNumbers) {
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
