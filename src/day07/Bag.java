package day07;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Bag {
    private final String name;
    private final Map<String, Integer> containedBags;

    private static Pattern noOtherBagsPattern;
    private static Pattern bagsPattern;
    {
        noOtherBagsPattern = Pattern.compile("(.*) bags contain no other bags.");
    }

    public Bag(String input) {
        String[] parts = input.split(" bags contain ");
        name = parts[0];
        containedBags = new HashMap<>();
        String contains = parts[1].substring(0, parts[1].length() - 1);
        if (!"no other bags".equals(contains)){
            Arrays.stream(contains.split(",")).forEach(bag -> {
                String[] bagInfo = bag.trim().split(" ", 2);
                int number = Integer.parseInt(bagInfo[0]);
                String color = bagInfo[1].split(" bag")[0];
                containedBags.put(color, number);
            });
        }
    }

    public String getColor(){
        return name;
    }

    public boolean containsColor(String color) {
        return containedBags.containsKey(color);
    }

    public boolean hasNoOtherbags() {
        return containedBags.isEmpty();
    }

    public Map<String, Integer> getContainedBags(){
        return containedBags;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(name, bag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
