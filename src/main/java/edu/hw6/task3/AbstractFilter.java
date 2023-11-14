package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter nextDirectoryStream) {
        return filter -> accept(filter) && nextDirectoryStream.accept(filter);
    }
}
