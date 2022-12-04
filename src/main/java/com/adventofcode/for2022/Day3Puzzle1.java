package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.*;

public class Day3Puzzle1 {
    final static String fileName = "day_3_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem(fileName);
        System.out.println("Solution to Day3Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private final static byte lowercaseOffset = 96;
        private final static byte uppercaseOffset = 38;

        public static String solve(List<String> problem) {
            List<Rucksack> rucksacks = parseProblem(problem);
            int totalScore = 0;
            for (Rucksack rucksack : rucksacks) {
                char sharedItem = getSharedItem(rucksack.leftCompartment, rucksack.rightCompartment);
                totalScore += getItemPriority(sharedItem);
            }
            return String.valueOf(totalScore);
        }

        private static List<Rucksack> parseProblem(List<String> problem) {
            List<Rucksack> result = new ArrayList<>();
            for (String rucksackInput : problem) {
                char[] leftCompartment = new char[rucksackInput.length() / 2];
                char[] rightCompartment = new char[rucksackInput.length() / 2];
                rucksackInput.getChars(0, rucksackInput.length() / 2, leftCompartment, 0);
                rucksackInput.getChars(rucksackInput.length() / 2, rucksackInput.length(), rightCompartment, 0);
                result.add(new Rucksack(leftCompartment, rightCompartment));
            }
            return result;
        }

        private static char getSharedItem(char[] leftCompartment, char[] rightCompartment) {
            for (char leftChar : leftCompartment) {
                for (char rightChar : rightCompartment) {
                    if (leftChar == rightChar) {
                        return leftChar;
                    }
                }
            }
            throw new RuntimeException(String.format("No shared item found left compartment [%s] right compartment [%s]",
                            Arrays.toString(leftCompartment),
                            Arrays.toString(rightCompartment)));
        }

        private static int getItemPriority(char item) {
            if (item < lowercaseOffset) {
                return item - uppercaseOffset;
            } else {
                return item - lowercaseOffset;
            }
        }


        private static class Rucksack {
            private final char[] leftCompartment;
            private final char[] rightCompartment;

            private Rucksack(char[] leftCompartment, char[] rightCompartment) {
                this.leftCompartment = leftCompartment;
                this.rightCompartment = rightCompartment;
            }
        }
    }


}
