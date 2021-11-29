import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Template {
    public static void main(String[] args) {
        new Template().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();

        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        return input;
    }

    public String solvePart2(String input) {
        return input;
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day01/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}