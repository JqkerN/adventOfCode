package main.java.com.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PuzzleUtil {
    final static String resourceRelativePath = "src/resources";

    public static List<String> readProblem(String inputFileName) throws IOException {
        Path inputFilePath = Paths.get(resourceRelativePath, inputFileName);
        return Files.readAllLines(inputFilePath);
    }
}
