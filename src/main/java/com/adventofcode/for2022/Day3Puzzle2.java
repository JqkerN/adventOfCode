package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3Puzzle2 {
    final static String fileName = "day_3_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem(fileName);
        System.out.println("Solution to Day3Puzzle2 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private final static byte lowercaseOffset = 96;
        private final static byte uppercaseOffset = 38;

        public static String solve(List<String> problem) {
            List<ElfGroup> elfGroups = parseProblem(problem);
            int totalScore = 0;
            for (ElfGroup elfGroup : elfGroups) {
                char sharedItem = getSharedItem(elfGroup.firstElf, elfGroup.secondElf, elfGroup.thirdElf);
                totalScore += getItemPriority(sharedItem);
            }
            return String.valueOf(totalScore);
        }

        private static List<ElfGroup> parseProblem(List<String> problem) {
            List<ElfGroup> result = new ArrayList<>();
            for (int i = 0; i < problem.size(); ) {
                result.add(new ElfGroup(problem.get(i++), problem.get(i++), problem.get(i++)));
            }
            return result;
        }

        private static char getSharedItem(char[] firstElf, char[] secondElf, char[] thirdElf) {
            for (char firstElfChar : firstElf) {
                for (char secondElfChar : secondElf) {
                    for (char thirdElfChar : thirdElf) {
                        if (firstElfChar == secondElfChar && firstElfChar == thirdElfChar) {
                            return firstElfChar;
                        }
                    }
                }
            }
            throw new RuntimeException(String.format("No shared item found firstElf [%s] secondElf [%s] thirdElf [%s]",
                    Arrays.toString(firstElf),
                    Arrays.toString(secondElf),
                    Arrays.toString(thirdElf)));
        }

        private static int getItemPriority(char item) {
            if (item < lowercaseOffset) {
                return item - uppercaseOffset;
            } else {
                return item - lowercaseOffset;
            }
        }


        private static class ElfGroup {
            private final char[] firstElf;
            private final char[] secondElf;
            private final char[] thirdElf;

            private ElfGroup(String firstElf, String secondElf, String thirdElf) {
                this.firstElf = new char[firstElf.length()];
                this.secondElf = new char[secondElf.length()];
                this.thirdElf = new char[thirdElf.length()];

                firstElf.getChars(0, firstElf.length(), this.firstElf, 0);
                secondElf.getChars(0, secondElf.length(), this.secondElf, 0);
                thirdElf.getChars(0, thirdElf.length(), this.thirdElf, 0);
            }
        }
    }


}
