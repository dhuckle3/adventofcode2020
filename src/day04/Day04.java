package day04;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day04 {
    public static void main(String[] args) {
        new Day04().run();
    }

    public void run() {
        String puzzleInput = getPuzzleInput();
        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    boolean isPassportValid(List<String> passportParts) {
        boolean byr = false;
        boolean iyr = false;
        boolean eyr = false;
        boolean hgt = false;
        boolean hcl = false;
        boolean ecl = false;
        boolean pid = false;

        for(String part : passportParts) {
            if (passportParts.size() > 8) {
                System.out.println(passportParts);
            }
            if(part.startsWith("byr")) {
                byr = true;
            } else if(part.startsWith("iyr")) {
                iyr = true;
            } else if(part.startsWith("eyr")) {
                eyr = true;
            } else if(part.startsWith("hgt")) {
                hgt = true;
            } else if(part.startsWith("hcl")) {
                hcl = true;
            } else if(part.startsWith("ecl")) {
                ecl = true;
            } else if(part.startsWith("pid")) {
                pid = true;
            }
        }
        return byr && iyr && eyr && hgt && hcl && ecl && pid;
    }

    boolean isPassportDataValid(List<String> passportParts) {
        Set<String> eyeColors = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

        boolean byr = false;
        boolean iyr = false;
        boolean eyr = false;
        boolean hgt = false;
        boolean hcl = false;
        boolean ecl = false;
        boolean pid = false;
        for (String part : passportParts) {
            String key = part.split(":")[0];
            String value = part.split(":")[1];
            switch(key) {
                case "byr":
                    int byrv = Integer.parseInt(value);
                    if (value.matches("[0-9]+") && value.length() == 4 && byrv >= 1920 && byrv <= 2002) {
                            byr = true;
                    }
                    break;
                case "iyr":
                    int iyrv = Integer.parseInt(value);
                    if (value.matches("[0-9]+") && value.length() == 4 && iyrv >= 2010 && iyrv <= 2020) {
                        iyr = true;
                    }
                    break;
                case "eyr":
                    int eyrv = Integer.parseInt(value);
                    if (value.matches("[0-9]+") && value.length() == 4 && eyrv >= 2020 && eyrv <= 2030) {
                        eyr = true;
                    }
                    break;
                case "hgt":
                    if (value.matches("[0-9]+cm")){
                        int hgtv = Integer.parseInt(value.substring(0, value.length()-2));
                        if (hgtv >= 150 && hgtv <= 193) {
                            hgt = true;
                        }
                    } else if (value.matches("[0-9]+in")) {
                        int hgtv = Integer.parseInt(value.substring(0, value.length() - 2));
                        if (hgtv >= 59 && hgtv <= 76) {
                            hgt = true;
                        }
                    }
                    break;
                case "hcl":
                    if (value.matches("#[0-9a-f]{6}")) {
                        hcl = true;
                    }
                    break;
                case "ecl":
                    if (eyeColors.contains(value)) {
                        ecl = true;
                    }
                    break;
                case "pid":
                    if(value.matches("[0-9]{9}")) {
                        pid = true;
                    }
                    break;
            }
        }
        return byr && iyr && eyr && hgt && hcl && ecl && pid;
    }

    public String solvePart1(String input) {
        int count = 0;
        String[] records = input.split("\n\n");
        for (String record : records) {
            record = record.replaceAll("\n", " ");
            List<String> parts = Arrays.asList(record.split(" "));
            if (isPassportValid(parts)) {
                count++;
            }
        }
        return String.format("There are %d valid passports", count);
    }

    public String solvePart2(String input) {
        int count = 0;
        String[] records = input.split("\n\n");
        for (String record : records) {
            record = record.replaceAll("\n", " ");
            List<String> parts = Arrays.asList(record.split(" "));
            if (isPassportDataValid(parts)) {
                count++;
            }
        }
        return String.format("There are %d valid passports", count);
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day04/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
