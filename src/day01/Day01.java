package day01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day01 {
    public static void main(String[] args) {
        new Day01().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();
        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        String[] lines = input.split("\n");

        String result = "";
        var numbers = Arrays.stream(lines).mapToInt(Integer::parseInt).sorted().toArray();
        // Asc sorted array of integers

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                // ignore cases where the indexes match
                if (i == j) continue;
                if (2020 == numbers[i] + numbers[j]) {
                    return String.format("%d", numbers[i]*numbers[j]);
                }
            }
        }
        throw new RuntimeException("No match found!");
    }

    public String solvePart2(String input) {
        String[] lines = input.split("\n");

        String result = "";
        int[] numbers = Arrays.stream(lines).mapToInt(Integer::parseInt).sorted().toArray();
        // Asc sorted array of integers

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length; k++) {
                    // ignore cases where the indexes match
                    if (i == j || j == k || i == k) {
                        continue;
                    }
                    if (2020 == numbers[i] + numbers[j] + numbers[k]) {
                        return String.format("%d", numbers[i] * numbers[j] * numbers[k]);
                    }
                }
            }
        }
        throw new RuntimeException("No match found!");
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day01/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}