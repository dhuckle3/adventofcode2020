package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryBoarding {
    public static void main(String[] args) {
        new BinaryBoarding().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();
        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        int maxSeatId = 0;
        String[] lines = input.split("\n");
        for (String line : lines) {
            BoardingPass pass = new BoardingPass(line);
            if (maxSeatId < pass.seatId) {
                maxSeatId = pass.seatId;
            }
        }
        return "maxSeatId: " + maxSeatId;
    }

    public String solvePart2(String input) {
        List<BoardingPass> passes = new ArrayList<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            BoardingPass pass = new BoardingPass(line);
            passes.add(pass);
        }
        passes.sort(Comparator.comparingInt(o -> o.seatId));
        int mySeat = 0;
        for (int i = 1; i < passes.size(); i++) {
            if (passes.get(i-1).seatId + 2 == passes.get(i).seatId) {
                mySeat = passes.get(i-1).seatId + 1;
                break;
            }
        }
        return "My seat: " + mySeat;
    }

    private class BoardingPass {
        public final int row;
        public final int column;
        public final int seatId;

        public BoardingPass(String inputValue) {
            String rowString = inputValue.substring(0, 7)
                .replaceAll("B", "1")
                .replaceAll("F", "0");
            String columnString = inputValue.substring(7, 10)
                .replaceAll("R", "1")
                .replaceAll("L", "0");
            row = Integer.parseInt(rowString, 2);
            column = Integer.parseInt(columnString, 2);
            seatId = row * 8 + column;
        }

        @Override
        public String toString() {
            return "BoardingPass{" +
                    "row=" + row +
                    ", column=" + column +
                    ", seatId=" + seatId +
                    '}';
        }
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day05/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}