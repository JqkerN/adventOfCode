package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Day5Puzzle1 {
    final static String fileName = "day_5_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem(fileName);
        System.out.println("Solution to Day5Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        public static String solve(List<String> problem) {
            List<List<String>> stackAndInstructionProblem = splitProblem(problem);
            List<Stack<String>> stacks = parseStacks(stackAndInstructionProblem.get(0));
            List<Instruction> instructions = parseInstructions(stackAndInstructionProblem.get(1));

            instructions.forEach(instruction -> applyInstruction(stacks, instruction));

            return getTheLastElementFromEachStack(stacks);
        }

        private static List<List<String>> splitProblem(List<String> problem) {
            List<String> stackPart = new ArrayList<>();
            List<String> instructionPart = new ArrayList<>();
            boolean firstPart = true;
            for (String input : problem) {
                if (input.isBlank()) {
                    firstPart = false;
                } else if (firstPart) {
                    stackPart.add(input);
                } else {
                    instructionPart.add(input);
                }
            }
            return List.of(stackPart, instructionPart);
        }

        private static List<Stack<String>> parseStacks(List<String> problem) {

            return null;
        }

        private static List<Instruction> parseInstructions(List<String> problem) {
            return null;
        }

        private static void applyInstruction(List<Stack<String>> stacks, Instruction instruction) {
            for (int ignored = 0; ignored < instruction.quantity; ignored++) {
                stacks.get(instruction.to).push(stacks.get(instruction.from).pop());
            }
        }

        private static String getTheLastElementFromEachStack(List<Stack<String>> stacks) {
            StringBuilder result = new StringBuilder();
            for (Stack<String> stack : stacks) {
                result.append(stack.pop());
            }
            return result.toString();
        }
    }

    private static class Instruction {
        public final int from;

        public final int to;
        public final int quantity;

        public Instruction(int from, int to, int quantity) {
            this.from = from;
            this.to = to;
            this.quantity = quantity;
        }
    }
}
