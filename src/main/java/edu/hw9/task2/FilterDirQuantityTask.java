package edu.hw9.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FilterDirQuantityTask extends RecursiveTask<List<String>> {
    private final Path startDir;
    private final int minQuantity;

    public FilterDirQuantityTask(Path startDir, int minQuantity) {
        this.startDir = startDir;
        this.minQuantity = minQuantity;
    }

    @Override
    @SneakyThrows
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        List<FilterDirQuantityTask> forks = new ArrayList<>();
        long filesCounter = countFilesInDirectory(startDir);
        try (Stream<Path> walk = Files.walk(startDir, 1)) {
            walk.filter(path -> Files.isDirectory(path) && !path.equals(startDir)).forEach(path -> {
                FilterDirQuantityTask nextTask = new FilterDirQuantityTask(path, minQuantity);
                nextTask.fork();
                forks.add(nextTask);
            });
            if (filesCounter > minQuantity) {
                result.add(startDir.toString());
            }
        }
        for (FilterDirQuantityTask task : forks) {
            result.addAll(task.join());
        }
        return result;
    }

    @SneakyThrows
    private long countFilesInDirectory(Path dir) {
        try (Stream<Path> files = Files.list(dir)) {
            return files.filter(Files::isRegularFile).count();
        }
    }
}
