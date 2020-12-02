package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class Day02 {
    public static void main(String[] args) {
        new Day02().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();
        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        int count = 0;
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (validPassword(line)) {
                count++;
            }
        }
        return String.format("Matches %d", count);
    }

    public String solvePart2(String input) {
        int count = 0;
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (reinterpretedValidPassword(line)) {
                count++;
            }
        }
        return String.format("Matches %d", count);
    }

    public boolean validPassword(String line) {
        String req = line.split(":")[0].trim();
        String minMax = req.substring(0, req.length()-1);
        int min = Integer.parseInt(minMax.split(" ")[0].split("-")[0].trim());
        int max = Integer.parseInt(minMax.split(" ")[0].split("-")[1].trim());
        String letter = req.split(" ")[1];
        String password = line.split(":")[1].trim();

        int result = Arrays.stream(password.split("")).mapToInt(x -> x.equals(letter) ? 1 : 0).reduce(Integer::sum).getAsInt();
        return result >= min && result <= max;
    }

    public boolean reinterpretedValidPassword(String line) {
        String req = line.split(":")[0].trim();
        String minMax = req.substring(0, req.length()-1);
        int pos1 = Integer.parseInt(minMax.split(" ")[0].split("-")[0].trim());
        int pos2 = Integer.parseInt(minMax.split(" ")[0].split("-")[1].trim());
        char letter = req.split(" ")[1].toCharArray()[0];
        String password = line.split(":")[1].trim();

        char[] chars = password.toCharArray();
        return chars[pos1 - 1] == letter ^ chars[pos2 - 1] == letter;
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day02/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}