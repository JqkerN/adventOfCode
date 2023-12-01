package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Puzzle2 {
    final static String fileName = "day_4_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day4Puzzle2 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        public static String solve(List<String> problem) {
            int totalScore = 0;
            List<AssignmentPair> assignmentPairs = parseProblem(problem);
            for (AssignmentPair assignmentPair : assignmentPairs) {
                totalScore += isFullyOverlapAssignments(assignmentPair.firstElf, assignmentPair.secondElf) ? 1 : 0;
            }
            return String.valueOf(totalScore);
        }

        private static boolean isFullyOverlapAssignments(Assignment assignment1, Assignment assignment2) {
            return assignment1.upperSection >= assignment2.lowerSection
                    && assignment1.lowerSection <= assignment2.upperSection;
        }

        private static List<AssignmentPair> parseProblem(List<String> problem) {
            List<AssignmentPair> parsedProblem = new ArrayList<>();
            for (String instructionsString : problem) {
                String[] instructions = instructionsString.split(",");
                String[] firstElf = instructions[0].split("-");
                String[] secondElf = instructions[1].split("-");
                parsedProblem.add(new AssignmentPair(new Assignment(firstElf[0], firstElf[1]), new Assignment(secondElf[0], secondElf[1])));
            }
            return parsedProblem;
        }
    }

    private static class AssignmentPair {
        private final Assignment firstElf;
        private final Assignment secondElf;

        private AssignmentPair(Assignment firstElf, Assignment secondElf) {
            this.firstElf = firstElf;
            this.secondElf = secondElf;
        }
    }

    private static class Assignment {
        private final int lowerSection;
        private final int upperSection;

        private Assignment(String lowerSection, String upperSection) {
            this.lowerSection = Integer.parseInt(lowerSection);
            this.upperSection = Integer.parseInt(upperSection);
        }
    }
}
