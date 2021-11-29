package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandheldHalting {
    public static void main(String[] args) {
        new HandheldHalting().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();
        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    /**
     * Returns the value of the accumulator when an infinite loop is detected.
     * @param program an array of commands in the form "cmd arg".
     * @return a 2 element int array with the first number being the status code and the second the accumulator value.
     * Status code: -1 indicates the program terminated because a loop was detected
     *               0 indicates the program completed normally.
     */
    public int[] runProgram(String[] program) {
        int[] result = new int[2];
        int accumulator = 0;
        int cursor = 0;
        int instruction = 1;
        int[] execution = new int[program.length];

        while(true) {
            if (cursor == program.length) {
                result[0] = 0;
                result[1] = accumulator;
                return result;
            }
            if (execution[cursor] != 0) {
                result[0] = -1;
                result[1] = accumulator;
                return result;
            }
            execution[cursor] = instruction;
            instruction++;

            // process command
            String cmd = program[cursor].split(" ")[0];
            int arg = Integer.parseInt(program[cursor].split(" ")[1]);


            switch (cmd) {
                case "acc":
                    accumulator += arg;
                    cursor++;
                    break;
                case "jmp":
                    cursor+= arg;
                    break;
                case "nop":
                    cursor++;
                    break;
            }
        }
    }

    public String solvePart1(String input) {
        String[] lines = input.split("\n");
        return String.format("%d", runProgram(lines)[1]);
    }

    public String solvePart2(String input) {
        String[] program = input.split("\n");
        for (int i = 0; i < program.length; i++) {
            String programCmdAtIndex = program[i].split(" ")[0];
            if (programCmdAtIndex.equals("nop") || programCmdAtIndex.equals("jmp")) {
                String[] modifiedProgram = program.clone();
                String[] parts = modifiedProgram[i].split(" ");
                if (parts[0].equals("nop")) {
                    parts[0] = "jmp";
                } else {
                    parts[0] = "nop";
                }
                modifiedProgram[i] = String.join(" ", parts);

                // run modified program...
                int[] result = runProgram(modifiedProgram);
                if (result[0] == 0) {
                    // program completed successfully
                    return String.format("%d", result[1]);
                }
            }
        }
        return "Failed to find a solution!";
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day08/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}