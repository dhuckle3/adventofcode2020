package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HandyHaversacks {
    Set<Bag> bags;

    public HandyHaversacks() {
        bags = new HashSet<>();
    }

    public static void main(String[] args) {
        new HandyHaversacks().run();
    }

    /**
     * find bags that can contain a color either directly or by containing a bag that can contain the color.
     * @param color
     * @return
     */
    public Integer canContainColor(String color) {
        Set<Bag> all = new HashSet<>(bags);
        Set<String> never = new HashSet<>();
        Set<String> contain = new HashSet<>();
        while (all.size() > 0) {
            for (Iterator<Bag> iterator = all.iterator(); iterator.hasNext(); ) {
                Bag bag = iterator.next();
                if(bag.getContainedBags().keySet().contains(color)) {
                    contain.add(bag.getColor());
                    iterator.remove();
                } else if(bag.hasNoOtherbags()) {
                    iterator.remove();
                    never.add(bag.getColor());
                } else {
                    if (allBagsAreKnownToBeNever(bag, never)) {
                        never.add(bag.getColor());
                        iterator.remove();
                    } else if (containsBagsThatContainColor(bag, contain)) {
                        contain.add(bag.getColor());
                        iterator.remove();
                    }
                }
            }
        }
        return contain.size();
    }

    /**
     * Return true if all bags that this bag can contain cannot contain the bag
     * @param bag - the bag we're checking
     * @param bags - the list of bags that cannot contain bags of a specific color
     * @return
     */
    private boolean allBagsAreKnownToBeNever(Bag bag, Set<String> bags) {
        Set<String> containedColors = bag.getContainedBags().keySet();
        return containedColors.stream().allMatch(bagColor -> bags.contains(bagColor));
    }

    private boolean containsBagsThatContainColor(Bag b, Set<String> bags) {
        return b.getContainedBags().keySet().stream().anyMatch(containedBagColor -> bags.contains(containedBagColor));
    }

    public void run() {
        String puzzleInput = getPuzzleInput();
        System.out.printf("Part 1: %s%n", solvePart1(puzzleInput));
        System.out.printf("Part 2: %s%n", solvePart2(puzzleInput));
    }

    public String solvePart1(String input) {
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Bag bag = new Bag(line);
            bags.add(bag);
        }

        return String.format("%d", canContainColor("shiny gold"));
    }

    public String solvePart2(String input) {
        return "";
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day07/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}