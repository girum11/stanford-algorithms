package io.girum.stanford.week1.inversions;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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

    @Test
    public void testStanfordTestCase() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("resources/week1/IntegerArray.txt")));
        int[] giantArray = new int[100000];
        int i = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            giantArray[i] = Integer.parseInt(line);
            i++;
        }
        reader.close();

        System.out.print("Found " + CountInversions.countInversions(giantArray) + " inversions.");
    }
}