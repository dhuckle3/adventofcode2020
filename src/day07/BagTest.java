package day07;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {
    @Test
    public void test_constructor_thatContainsNoOtherBags() {
        Bag bag = new Bag("dark maroon bags contain no other bags.");
        assertEquals("dark maroon", bag.getColor());
    }

    @Test
    public void test_constructor() {
        Bag bag = new Bag("dim plum bags contain 1 bright purple bag, 2 posh red bags.");
        assertEquals("dim plum", bag.getColor());
        Map<String, Integer> bags = bag.getContents();
        assertEquals(1, bags.get("bright purple"));
        assertEquals(2, bags.get("posh red"));
    }
}