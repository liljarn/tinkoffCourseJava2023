package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilesTreeHandler {
    private static final ForkJoinPool FORK_JOIN_POOL = ForkJoinPool.commonPool();

    @SneakyThrows
    public static List<Path> findDirectoriesWithMoreThanNumberFiles(Path root, int number) {
        List<Path> result;
        result = FORK_JOIN_POOL.invoke(new FilterDirQuantityTask(root, number));
        FORK_JOIN_POOL.shutdown();
        return result;
    }

    public static List<Path> filterFilesByPredicate(Path root, Predicate<Path> predicate) {
        List<Path> result;
        result = FORK_JOIN_POOL.invoke(new FilterFilesPredicateTask(root, predicate));
        FORK_JOIN_POOL.shutdown();
        return result;
    }
}
