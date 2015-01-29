package io.girum.stanford.week1.inversions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CountInversionsTest {

    @Test
    public void testCountInversions() {
        assertEquals(CountInversions.countInversions(new int[]{1, 2, 3}), 0);
        assertEquals(CountInversions.countInversions(new int[]{3, 2, 1}), 3);
    }

    @Test
    public void testNatisTestCases() throws Exception {
        assertEquals(CountInversions.countInversions(new int[]{8, 4, 7, 2, 11}), 5);
        assertEquals(CountInversions.countInversions(new int[]{3, 2, 4, 1}), 4);
        assertEquals(CountInversions.countInversions(new int[]{9, 3, 6, 2, 4, 7}), 8);
        assertEquals(CountInversions.countInversions(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), 0);
        assertEquals(CountInversions.countInversions(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}), 36);
    }
}