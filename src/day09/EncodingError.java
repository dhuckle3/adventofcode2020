package day09;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class EncodingError {
    private double findError(double[] program, int preamble) {
        for(int cursor = preamble; cursor < program.length; cursor++) {
            if (!isValid(program, preamble, cursor)) {
                System.out.println(String.format("Error found at cursor pos:%d, value was %.0f", cursor, program[cursor]));
                return program[cursor];
            }
        }
        return -1;
    }

    public boolean isValid(double[] program, int windowLength, int cursor) {
        double[] window = new double[windowLength];
        double value = program[cursor];

        for (int i = 0; i < windowLength; i++) {
            window[i] = program[cursor -  windowLength + i];
        }
        for (int i = 0; i < window.length; i++) {
            for (int j = 0; j < window.length; j++) {
                if (i != j) {
                    if ((window[i] + window[j]) == value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int[] slidingWindow(double[] program, double value) {
        int start = 0;
        int end = 1;

        /**
         *   [0,1,2,3,4,5]
         *   0,1,2,3
         */
        while(true) {
            double sum = program[start] + program[end];
            for (int i = 0; i < 1000; i++) {
                if (sum == value) {
                    int[] result = {start, end};
                    return result;
                } else if (sum > value) {
                    start++;
                    end = start + 1;
                    break;
                } else {
                    end++;
                    sum += program[end];
                }
            }
        }
    }

    public static void main(String[] args) {
        new EncodingError().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();

        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        double[] program = Arrays.stream(input.split("\n")).mapToDouble(Double::parseDouble).toArray();
        return String.format("%.0f", findError(program, 25));
    }

    public String solvePart2(String input) {
        double[] program = Arrays.stream(input.split("\n")).mapToDouble(Double::parseDouble).toArray();
        double value = findError(program, 25);
        int[] res = slidingWindow(program, value);

        double smallest = Double.MAX_VALUE;
        double largest = Double.MIN_VALUE;
        for (int i = res[0]; i <= res[1]; i++) {
            if (program[i] < smallest) {
                smallest = program[i];
            }
            if (program[i] > largest) {
                largest = program[i];
            }
        }
        return String.format("%.0f", smallest + largest);
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day09/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}