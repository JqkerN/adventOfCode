package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.List;


public class Day2Puzzle1 {
    final static String fileName = "day_2_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem(fileName);
        System.out.println("Solution to Day2Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private final static int winReward = 6;
        private final static int drawReward = 3;
        private final static int lossReward = 0;

        public static String solve(List<String> problem) {
            int totalScore = 0;
            for (String movesString : problem) {
                String[] moves = movesString.split(" ");
                Move opponentMove = Move.getMove(moves[0]);
                Move myMove = Move.getMove(moves[1]);
                totalScore += getRockPaperScissorScore(myMove, opponentMove);
                totalScore += myMove.getPoint();
            }
            return String.valueOf(totalScore);
        }

        private static int getRockPaperScissorScore(Move myMove, Move opponentMove) {
            if(myMove == opponentMove) return drawReward;
            if (myMove == Move.ROCK && opponentMove == Move.SCISSOR) return winReward;
            if (myMove == Move.PAPER && opponentMove == Move.ROCK) return winReward;
            if (myMove == Move.SCISSOR && opponentMove == Move.PAPER) return winReward;
            return lossReward;
        }

        private enum Move {
            ROCK(1), PAPER(2), SCISSOR(3);

            int point;

            Move(int point) {
                this.point = point;
            }

            public int getPoint() {
                return point;
            }

            private static Move getMove(String move) {
                return switch (move) {
                    case "X", "A" -> ROCK;
                    case "Y", "B" -> PAPER;
                    case "Z", "C" -> SCISSOR;
                    default -> throw new RuntimeException("Invalid move: " + move);
                };
            }
        }
    }



}
