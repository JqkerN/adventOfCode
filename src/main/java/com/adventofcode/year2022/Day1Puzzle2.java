package main.java.com.adventofcode.year2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.*;

public class Day1Puzzle2 {
    final static String fileName = "day_1_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day1Puzzle2 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        public static String solve(List<String> problem) {
            List<Integer> topElvCarrierCalories = getElvCarriersCalories(problem);
            int sumOfTopThreeCarriers = getSumOfTopCarriers(topElvCarrierCalories, 3);
            return String.valueOf(sumOfTopThreeCarriers);
        }

        private static List<Integer> getElvCarriersCalories(List<String> problem) {
            List<Integer> elvCarrieCalories = new ArrayList<>();
            int currentElfCalories = 0;
            for (String calories : problem) {
                if (calories.isBlank()) {
                    elvCarrieCalories.add(currentElfCalories);
                    currentElfCalories = 0;
                    continue;
                }
                currentElfCalories = currentElfCalories + Integer.parseInt(calories);
            }
            elvCarrieCalories.sort(Integer::compareTo);
            return elvCarrieCalories;
        }

        private static int getSumOfTopCarriers(List<Integer> topElvCarrierCalories, int numberOfTopCarriers) {
            int sumOfTopThreeCarriers = 0;
            for (int ignored = 0; ignored < Math.min(topElvCarrierCalories.size(), numberOfTopCarriers); ignored++) {
                sumOfTopThreeCarriers += topElvCarrierCalories.remove(topElvCarrierCalories.size() - 1);
            }
            return sumOfTopThreeCarriers;
        }

    }
}
