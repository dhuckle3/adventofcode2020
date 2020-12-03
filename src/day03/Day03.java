package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day03 {
    public static void main(String[] args) {
        new Day03().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();

        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        return String.format("Trees Encountered %d", treesOnPath(input, 3, 1));
    }

    private int treesOnPath(String input, int right, int down) {
        String[] lines = input.split("\n");
        int cursor = 0;
        int treesEncountered = 0;
        for (int i = 0; i < lines.length; i+= down) {
            String line = lines[i];
            int index = cursor % (line.length());
            if (line.charAt(index) == '#') {
                treesEncountered++;
            }
            cursor += right;
        }
        return treesEncountered;
    }

    public String solvePart2(String input) {
        long treeTotal = 1;

        int trees = treesOnPath(input, 1, 1);
        treeTotal = trees * treeTotal;
        System.out.printf("1, 1, %s\n", trees);

        trees = treesOnPath(input, 3, 1);
        treeTotal = trees * treeTotal;
        System.out.printf("3, 1, %s\n", trees);

        trees = treesOnPath(input, 5, 1);
        treeTotal = trees * treeTotal;
        System.out.printf("5, 1, %s\n", trees);

        trees = treesOnPath(input, 7, 1);
        treeTotal = trees * treeTotal;
        System.out.printf("7, 1, %s\n", trees);

        trees = treesOnPath(input, 1, 2);
        treeTotal = trees * treeTotal;
        System.out.printf("1, 2, %s\n", trees);


        return "" + treeTotal;
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day03/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
