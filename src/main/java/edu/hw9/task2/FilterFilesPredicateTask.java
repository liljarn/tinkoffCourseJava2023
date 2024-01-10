package edu.hw9.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class FilterFilesPredicateTask extends RecursiveTask<List<Path>> {
    private final Path startDir;
    private final Predicate<Path> predicate;

    public FilterFilesPredicateTask(Path startDir, Predicate<Path> predicate) {
        this.startDir = startDir;
        this.predicate = predicate;
    }

    @Override
    @SneakyThrows
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        List<FilterFilesPredicateTask> forks = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(startDir, 1)) {
            walk.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    if (predicate.test(path)) {
                        result.add(path);
                    }
                } else if (Files.isDirectory(path) && !path.equals(startDir)) {
                    FilterFilesPredicateTask nextTask = new FilterFilesPredicateTask(path, predicate);
                    nextTask.fork();
                    forks.add(nextTask);
                }
            });
        }
        for (FilterFilesPredicateTask task : forks) {
            result.addAll(task.join());
        }
        return result;
    }
}
