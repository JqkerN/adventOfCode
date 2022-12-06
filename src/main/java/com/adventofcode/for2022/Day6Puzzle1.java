package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.*;

public class Day6Puzzle1 {
    final static String fileName = "day_6_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem(fileName);
        System.out.println("Solution to Day6Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {

        public static String solve(List<String> problem) {
            final var dataStream = parseProblem(problem);
            var markerIndex = findMarkerEndRecursive(dataStream, 0, 4);
            return String.valueOf(markerIndex);
        }

        private static int findMarkerEndRecursive(List<String> dataStream, int position, int numOfLookAhead) {
            if (position + numOfLookAhead > dataStream.size()) return -1;

            if (isMarker(dataStream, position, numOfLookAhead)) return position + numOfLookAhead;

            return findMarkerEndRecursive(dataStream, position + 1, numOfLookAhead);
        }

        private static boolean isMarker(List<String> dataStream, int position, int numOfLookAhead) {
            var cache = new HashSet<>();
            for (int offset = 0; offset < numOfLookAhead; offset++) {
                cache.add(dataStream.get(position + offset));
            }
            return cache.size() == numOfLookAhead;
        }

        private static List<String> parseProblem(List<String> problem) {
            String[] dataStream = problem.get(0).split("");
            return List.of(dataStream);
        }
    }
}
