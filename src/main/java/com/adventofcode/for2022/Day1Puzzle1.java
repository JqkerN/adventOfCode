package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.List;

public class Day1Puzzle1 {
    final static String fileName = "day_1_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day1Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        public static String solve(List<String> problem) {
            int maxCalories = Integer.MIN_VALUE;
            int currentElfCalories = 0;
            for (String calories : problem) {
                if (calories.isBlank()) {
                    currentElfCalories = 0;
                    continue;
                }
               currentElfCalories += Integer.parseInt(calories);
               if (currentElfCalories > maxCalories) maxCalories = currentElfCalories;
            }
            return String.valueOf(maxCalories);
        }
    }
}
