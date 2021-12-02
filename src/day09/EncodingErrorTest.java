package day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodingErrorTest {

    @Test
    void test_isValid() {
        EncodingError ee = new EncodingError();
        double[] program = {1,1,2,3,5};
        assertTrue(ee.isValid(program, 2, 2));
        assertTrue(ee.isValid(program, 2, 3));
        assertTrue(ee.isValid(program, 2, 4));
    }

    @Test
    void test_slidingWindow() {
        double[] program = {35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576};
        double value = 127;

        EncodingError ee = new EncodingError();
        int[] result = ee.slidingWindow(program, 127);
        assertEquals(15, result[0]);
        assertEquals(40, result[1]);
    }
}