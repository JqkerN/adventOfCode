package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.List;

public class Day2Puzzle2 {
    final static String fileName = "day_2_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day2Puzzle2 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private final static int winReward = 6;
        private final static int drawReward = 3;
        private final static int lossReward = 0;

        public static String solve(List<String> problem) {
            int totalScore = 0;
            for (String moveStrategyString : problem) {
                String[] moveStrategy = moveStrategyString.split(" ");
                Move opponentMove = Move.getMove(moveStrategy[0]);
                Move myMove = calculateNextMoveFromStrategy(moveStrategy[1], opponentMove);
                totalScore += getRockPaperScissorScore(myMove, opponentMove);
                totalScore += myMove.getPoint();
            }
            return String.valueOf(totalScore);
        }

        private static Move calculateNextMoveFromStrategy(String strategy, Move opponentMove) {
            return switch (strategy) {
                case "X" -> getLosingMove(opponentMove);
                case "Y" -> getDrawMove(opponentMove);
                case "Z" -> getWinningMove(opponentMove);
                default -> throw new RuntimeException("Invalid strategy: " + strategy);
            };
        }

        private static Move getWinningMove(Move opponentMove) {
            return switch (opponentMove) {
                case ROCK -> Move.PAPER;
                case PAPER -> Move.SCISSOR;
                case SCISSOR -> Move.ROCK;
            };
        }

        private static Move getDrawMove(Move opponentMove) {
            return opponentMove;
        }

        private static Move getLosingMove(Move opponentMove) {
            return switch (opponentMove) {
                case ROCK -> Move.SCISSOR;
                case PAPER -> Move.ROCK;
                case SCISSOR -> Move.PAPER;
            };
        }

        private static int getRockPaperScissorScore(Move myMove, Move opponentMove) {
            if (myMove == opponentMove) return drawReward;
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

            private int getPoint() {
                return point;
            }

            private static Move getMove(String move) {
                return switch (move) {
                    case "A" -> ROCK;
                    case "B" -> PAPER;
                    case "C" -> SCISSOR;
                    default -> throw new RuntimeException("Invalid move: " + move);
                };
            }
        }
    }


}
