package day07;

import java.util.*;
public class Bag {
    private final String color;
    private final Map<String, Integer> contents;

    public Bag(String input) {
        String[] parts = input.split(" bags contain ");
        color = parts[0];
        contents = new HashMap<>();
        String contains = parts[1].substring(0, parts[1].length() - 1);
        if (!"no other bags".equals(contains)){
            Arrays.stream(contains.split(",")).forEach(bag -> {
                String[] bagInfo = bag.trim().split(" ", 2);
                int number = Integer.parseInt(bagInfo[0]);
                String color = bagInfo[1].split(" bag")[0];
                contents.put(color, number);
            });
        }
    }

    public String getColor(){
        return color;
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public Map<String, Integer> getContents(){
        return contents;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(color, bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
