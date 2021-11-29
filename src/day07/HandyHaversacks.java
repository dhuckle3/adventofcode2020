package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HandyHaversacks {
    Set<Bag> bagRules;
    Map<String, Bag> bagMap;

    public HandyHaversacks() {
        bagRules = new HashSet<>();
        bagMap = new HashMap<>();
    }

    public static void main(String[] args) {
        new HandyHaversacks().run();
    }

    /**
     * Count the bags that can contain a bag of a specific color. Bags can contain bags that contain the colored bag recursively.
     * @param color the color of bag that we're searching for.
     * @return a count of bags that can directly or indirectly contain a bag of this color.
     */
    public Integer canContainColor(String color) {
        Set<Bag> all = new HashSet<>(bagRules);
        Set<String> never = new HashSet<>();
        Set<String> contain = new HashSet<>();
        while (all.size() > 0) {
            for (Iterator<Bag> iterator = all.iterator(); iterator.hasNext(); ) {
                Bag bag = iterator.next();
                if(bag.getContents().keySet().contains(color)) {
                    contain.add(bag.getColor());
                    iterator.remove();
                } else if(bag.isEmpty()) {
                    iterator.remove();
                    never.add(bag.getColor());
                } else {
                    if (bagContainsBagsOnlyInSet(bag, never)) {
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
     * Return true if the bag contains bags only
     * Return true if all bags that this bag can contain cannot contain the bag
     * @param bag - the bag we're checking
     * @param bags - the list of bags that cannot contain bags of a specific color
     * @return bagContainsOnlyBagsInSet
     */
    private boolean bagContainsBagsOnlyInSet(Bag bag, Set<String> bags) {
        Set<String> contents = bag.getContents().keySet();
        return contents.stream().allMatch(bagColor -> bags.contains(bagColor));
    }

    private boolean containsBagsThatContainColor(Bag b, Set<String> bags) {
        return b.getContents().keySet().stream().anyMatch(containedBagColor -> bags.contains(containedBagColor));
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
            bagRules.add(bag);
            bagMap.put(bag.getColor(), bag);
        }

        return String.format("%d", canContainColor("shiny gold"));
    }

    public String solvePart2(String input) {
        Bag shinyGoldBag = bagMap.get("shiny gold");
        Map<String, Integer> bags = new HashMap<>(shinyGoldBag.getContents());

        int count = 0;
        while (!bags.isEmpty()) {
            for (String color : new HashSet<>(bags.keySet())) {
                Bag b = bagMap.get(color);
                if (b.isEmpty()) {
                    Integer bagCount = bags.remove(color);
                    count += bagCount;
                } else {
                    Integer bagCount = bags.remove(color);
                    count += bagCount;
                    b.getContents().forEach((c, i) -> {
                        if (!bags.containsKey(c)) {
                            bags.put(c, 0);
                        }
                        bags.put(c, bags.get(c) + (bagCount * i));
                    });
                }
            }
        }
        return String.format("%d", count);
    }

    public String getPuzzleInput() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/day07/input.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}