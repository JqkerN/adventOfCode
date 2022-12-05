package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5Puzzle1 {
    final static String fileName = "day_5_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem(fileName);
        System.out.println("Solution to Day5Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private static final Pattern EXTRACT_INSTRUCTION_PATTERN = Pattern.compile("move (\\d{1,3}) from (\\d{1,3}) to (\\d{1,3})");
        private static final IntFunction<Integer> CALCULATE_NEXT_CHAR_INDEX = (currentCharIndex) -> 1 + 4 * currentCharIndex;

        public static String solve(List<String> problem) {
            List<List<String>> stackAndInstructionProblem = splitProblem(problem);
            List<Stack<Character>> stacks = parseStacks(stackAndInstructionProblem.get(0));
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

        private static List<Stack<Character>> parseStacks(List<String> problem) {
            List<Stack<Character>> stacks = new ArrayList<>();

            String stackIndices = problem.remove(problem.size() - 1);
            int numberOfStacks = Character.digit(stackIndices.charAt(stackIndices.length() - 2), 10);

            for (int i = 0; i < numberOfStacks; i++) {
                stacks.add(new Stack<>());
            }

            for (int i = problem.size() - 1; i >= 0; i--) {
                String stackValues = problem.get(i);
                for (int j = 0; j < stacks.size(); j++) {
                    int index = CALCULATE_NEXT_CHAR_INDEX.apply(j);
                    char item = stackValues.charAt(index);
                    if (item != ' ') {
                        stacks.get(j).push(item);
                    }
                }
            }

            return stacks;
        }

        private static List<Instruction> parseInstructions(List<String> problem) {
            List<Instruction> instructions = new ArrayList<>();
            for (String instruction : problem) {
                Matcher instructionMatcher = EXTRACT_INSTRUCTION_PATTERN.matcher(instruction);
                if (instructionMatcher.matches()) {
                    String quantity = instructionMatcher.group(1);
                    String from = instructionMatcher.group(2);
                    String to = instructionMatcher.group(3);
                    instructions.add(new Instruction(Integer.parseInt(from) - 1, Integer.parseInt(to) - 1, Integer.parseInt(quantity)));
                } else {
                    throw new RuntimeException("Could not parse instruction: " + instruction);
                }
            }
            return instructions;
        }

        private static void applyInstruction(List<Stack<Character>> stacks, Instruction instruction) {
            for (int ignored = 0; ignored < instruction.quantity; ignored++) {
                stacks.get(instruction.to).push(stacks.get(instruction.from).pop());
            }
        }

        private static String getTheLastElementFromEachStack(List<Stack<Character>> stacks) {
            StringBuilder result = new StringBuilder();
            for (Stack<Character> stack : stacks) {
                Character lastElement = stack.pop();
                result.append(lastElement);
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
