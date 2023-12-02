package main.java.com.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PuzzleUtil {
    private final static String RESOURCE_RELATIVE_PATH = "src/resources";

    public static List<String> readProblem(String year, String inputFileName) throws IOException {
        Path inputFilePath = Paths.get(RESOURCE_RELATIVE_PATH, "year%s/%s".formatted(year, inputFileName));
        return Files.readAllLines(inputFilePath);
    }
}
