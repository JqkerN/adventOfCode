package main.java.com.adventofcode.year2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.List;

public class Day8Puzzle2 {
    final static String fileName = "day_8_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day8Puzzle2 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {

        public static String solve(List<String> problem) {
            int[][] trees = parseProblem(problem);
            int count = countVisibleTrees(trees);
            return String.valueOf(count);
        }

        private static int countVisibleTrees(int[][] trees) {
            int maxScenicScore = Integer.MIN_VALUE;

            for (int row = 0; row < trees.length; row++) {
                for (int col = 0; col < trees[1].length; col++) {
                    int longestDistance1 = 0;
                    int longestDistance2 = 0;
                    int longestDistance3 = 0;
                    int longestDistance4 = 0;

                    int currentTreeHight = trees[row][col];
                    if (row == 0 || col == 0) {
                        continue;
                    }
                    if (row == trees.length - 1 || col == trees[1].length - 1) {
                        continue;
                    }
                    for (int rowSearch = row - 1; rowSearch > -1; rowSearch--) {
                        longestDistance1++;
                        if (currentTreeHight <= trees[rowSearch][col]) {
                            break;
                        }
                    }
                    for (int rowSearch = row + 1; rowSearch < trees.length; rowSearch++) {
                        longestDistance2++;
                        if (currentTreeHight <= trees[rowSearch][col]) {
                            break;
                        }
                    }
                    for (int colSearch = col - 1; colSearch > -1; colSearch--) {
                        longestDistance3++;
                        if (currentTreeHight <= trees[row][colSearch]) {
                            break;
                        }
                    }
                    for (int colSearch = col + 1; colSearch < trees[1].length; colSearch++) {
                        longestDistance4++;
                        if (currentTreeHight <= trees[row][colSearch]) {
                            break;
                        }
                    }
                    int currentScenicScore = longestDistance1 * longestDistance2 * longestDistance3 * longestDistance4;
                    if (maxScenicScore < currentScenicScore) {
                        maxScenicScore = currentScenicScore;
                    }
                }
            }

            return maxScenicScore;
        }

        private static int[][] parseProblem(List<String> problem) {
            int[][] trees = new int[problem.size()][];
            for (int i = 0; i < problem.size(); i++) {
                String[] rowOfTrees = problem.get(i).split("");
                trees[i] = new int[rowOfTrees.length];
                for (int j = 0; j < rowOfTrees.length; j++) {
                    trees[i][j] = Integer.parseInt(rowOfTrees[j]);
                }
            }
            return trees;
        }

    }
}
