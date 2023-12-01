package main.java.com.adventofcode.for2022;

import main.java.com.adventofcode.PuzzleUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class Day7Puzzle2 {
    final static String fileName = "day_7_p1.txt";

    public static void main(String[] args) throws IOException {
        List<String> problem = PuzzleUtil.readProblem("2022", fileName);
        System.out.println("Solution to Day7Puzzle2 = " + PuzzleSolver.solve(problem));
    }

    public static class PuzzleSolver {
        private static final int FILESYSTEM_SIZE = 70_000_000;
        private static final int NEEDED_SPACE_FOR_UPDATE = 30_000_000;
        private static final BiPredicate<Integer, Integer> CANDIDATE_TO_BE_REMOVED = (size, freeSpace) -> freeSpace + size > NEEDED_SPACE_FOR_UPDATE;

        public static String solve(List<String> problem) {
            Folder rootFolder = parseProblem(problem);
            int usedSpace = countFoldersRecursive(rootFolder);
            int freeSpace = FILESYSTEM_SIZE - usedSpace;
            int minFolderSpaceToRemove = findMinFolderRecursive(rootFolder, freeSpace, CANDIDATE_TO_BE_REMOVED);
            return String.valueOf(minFolderSpaceToRemove);
        }

        private static int findMinFolderRecursive(Folder currentFolder, int freeSpace, BiPredicate<Integer, Integer> filter) {
            int currentMin = Integer.MAX_VALUE;
            if (currentFolder.getSubFolders().isEmpty()) {
                int size = currentFolder.getSize();
                if (filter.test(size, freeSpace)) {
                    if (size < currentMin){
                        currentMin = size;
                    }
                }
                return currentMin;
            }

            for (Folder subFolder : currentFolder.getSubFolders().values()) {
                if (filter.test(subFolder.getSize(), freeSpace)) {
                    if (subFolder.getSize() < currentMin){
                        currentMin = subFolder.getSize();
                    }
                }

                int min =  findMinFolderRecursive(subFolder, freeSpace, filter);
                if (filter.test(min, freeSpace)) {
                    if (min < currentMin){
                        currentMin = min;
                    }
                }
            }
            return currentMin;
        }

        private static int countFoldersRecursive(Folder currentFolder) {
            int folderSize = currentFolder.getFiles().values().stream()
                    .map(file -> file.fileSize)
                    .reduce(0, Integer::sum);


            for (Folder subFolder : currentFolder.getSubFolders().values()) {
                int result = countFoldersRecursive(subFolder);
                folderSize += result;
            }
            currentFolder.setSize(folderSize);
            return folderSize;
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
        @Override
        public String toString() {
            return "Folder{" +
                    "size=" + size +
                    ", name='" + name + '\'' +
                    '}';
        }

        private int size;
        private final String name;
        private final Folder parentFolder;
        private final Map<String, File> files = new HashMap<>();
        private final Map<String, Folder> subFolders = new HashMap<>();

        private Folder(String name, Folder parentFolder) {
            this.size = -1;
            this.name = name;
            this.parentFolder = parentFolder;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
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
