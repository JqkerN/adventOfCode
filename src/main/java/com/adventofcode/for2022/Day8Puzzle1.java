package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.List;

public class Day8Puzzle1 {
    final static String fileName = "day_8_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day8Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {

        public static String solve(List<String> problem) {
            int[][] trees = parseProblem(problem);
            int count = countVisibleTrees(trees);
            return String.valueOf(count);
        }

        private static int countVisibleTrees(int[][] trees) {
            int count = 0;

            for (int row = 0; row < trees.length; row++) {
                for (int col = 0; col < trees[1].length; col++) {
                    boolean isVisible1 = true;
                    boolean isVisible2 = true;
                    boolean isVisible3 = true;
                    boolean isVisible4 = true;
                    int currentTreeHight = trees[row][col];
                    if (row == 0 || col == 0) {
                        count++;
                        continue;
                    }
                    if (row == trees.length - 1 || col == trees[1].length - 1) {
                        count++;
                        continue;
                    }
                    for (int rowSearch = 0; rowSearch < row; rowSearch++) {
                        if (currentTreeHight <= trees[rowSearch][col]) {
                            isVisible1 = false;
                            break;
                        }
                    }
                    for (int rowSearch = row + 1; rowSearch < trees.length; rowSearch++) {
                        if (currentTreeHight <= trees[rowSearch][col]) {
                            isVisible2 = false;
                            break;
                        }
                    }
                    for (int colSearch = 0; colSearch < col; colSearch++) {
                        if (currentTreeHight <= trees[row][colSearch]) {
                            isVisible3 = false;
                            break;
                        }
                    }
                    for (int colSearch = col + 1; colSearch < trees[1].length; colSearch++) {
                        if (currentTreeHight <= trees[row][colSearch]) {
                            isVisible4 = false;
                            break;
                        }
                    }
                    if (isVisible1 || isVisible2 || isVisible3 || isVisible4) {
                        count++;
                    }
                }
            }

            return count;
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
