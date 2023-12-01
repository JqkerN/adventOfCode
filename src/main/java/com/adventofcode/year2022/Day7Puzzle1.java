package main.java.com.adventofcode.year2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.*;
import java.util.function.IntPredicate;

public class Day7Puzzle1 {
    final static String fileName = "day_7_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day7Puzzle1 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private static final IntPredicate IS_LESS_OR_EQUAL_TO_100000 = (size) -> size < 100_000;

        public static String solve(List<String> problem) {
            Folder rootFolder = parseProblem(problem);
            int[] result = countFoldersRecursive(rootFolder, IS_LESS_OR_EQUAL_TO_100000);
            System.out.println(Arrays.toString(result));
            return String.valueOf(result[2]);
        }

        private static int[] countFoldersRecursive(Folder currentFolder, IntPredicate shouldBeCounted) {
            int sumOfFolderSize = 0;
            int currentCount = 0;
            int folderSize = currentFolder.getFiles().values().stream()
                    .map(file -> file.fileSize)
                    .reduce(0, Integer::sum);


            for (Folder subFolder : currentFolder.getSubFolders().values()) {
                int[] result = countFoldersRecursive(subFolder, shouldBeCounted);
                currentCount += result[0];
                folderSize += result[1];
                sumOfFolderSize += result[2];
            }

            currentCount += shouldBeCounted.test(folderSize) ? 1 : 0;
            sumOfFolderSize += shouldBeCounted.test(folderSize) ? folderSize : 0;
            return new int[] {currentCount, folderSize, sumOfFolderSize};
        }

        private static Folder parseProblem(List<String> problem) {
            problem.remove(0);
            Folder currentFolder = new Folder("/", null);

            for (String command_or_output : problem) {
                if (command_or_output.startsWith("$")) {
                    currentFolder = parseCommand(command_or_output, currentFolder);
                } else {
                    parseOutput(command_or_output, currentFolder);
                }
            }

            return getRootFolder(currentFolder);
        }

        private static Folder parseCommand(String command, Folder currentFolder) {
            if (command.contains("cd ..")) {
                currentFolder = currentFolder.getParentFolder();
            } else if (command.contains("cd /")) {
                currentFolder = getRootFolder(currentFolder);
            } else if (command.contains("cd ")) {
                String folderName = command.substring(5);
                currentFolder = currentFolder.getSubFolders().get(folderName);
            }
            return currentFolder;
        }

        private static Folder getRootFolder(Folder currentFolder) {
            while (currentFolder.parentFolder != null) currentFolder = currentFolder.getParentFolder();
            return currentFolder;
        }

        private static void parseOutput(String output, Folder currentFolder) {
            String[] outputParts = output.split(" ");
            if ("dir".equalsIgnoreCase(outputParts[0])) {
                currentFolder.addSubFolder(new Folder(outputParts[1], currentFolder));
            } else {
                currentFolder.addFile(new File(outputParts[1], Integer.parseInt(outputParts[0])));
            }
        }
    }

    private static class Folder {
        private final String name;
        private final Folder parentFolder;
        private final Map<String, File> files = new HashMap<>();
        private final Map<String, Folder> subFolders = new HashMap<>();

        private Folder(String name, Folder parentFolder) {
            this.name = name;
            this.parentFolder = parentFolder;
        }

        private void addFile(File file) {
            files.put(file.name, file);
        }

        private void addSubFolder(Folder folder) {
            subFolders.put(folder.name, folder);
        }

        private Map<String, File> getFiles() {
            return files;
        }

        private Map<String, Folder> getSubFolders() {
            return subFolders;
        }

        private Folder getParentFolder() {
            return parentFolder;
        }
    }

    private static record File(String name, int fileSize) {
    }
}
