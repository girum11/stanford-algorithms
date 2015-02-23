package io.girum.stanford.week2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.testng.Assert.*;

public class QuicksortTest {


    @DataProvider(name = "numbers")
    public static Object[][] numbers() {
        return new Object[][]{
                new Object[]{new int[]{}, new int[]{}},
                new Object[]{new int[]{1}, new int[]{1}},
                new Object[]{new int[]{1, 2}, new int[]{1, 2}},
                new Object[]{new int[]{2, 1}, new int[]{1, 2}},
                new Object[]{new int[]{1, 2, 3}, new int[]{1, 2, 3}},
                new Object[]{new int[]{3, 2, 1}, new int[]{1, 2, 3}},
                new Object[]{new int[]{3, 1, 4, 8, 2, 3}, new int[]{1, 2, 3, 3, 4, 8}},
                new Object[]{new int[]{4, 3, 8, 1, 2, 4, 6, 9}, new int[]{1, 2, 3, 4, 4, 6, 8, 9}},
        };
    }

    @Test(dataProvider = "numbers")
    public void testQuicksort(int[] actual, int[] expected) throws Exception {
        Quicksort.quicksort(actual);
        assertEquals(actual, expected, print(actual) + " || " + print(expected));
    }

    private String print(int[] array) {
        StringBuilder output = new StringBuilder(array.length * 3);
        output.append("{");

        for (int number : array) {
            output.append(number).append(", ");
        }

        if (output.length() >= 2) {
            output.delete(output.length() - 2, output.length());
        }

        output.append("}");
        return output.toString();
    }

    @DataProvider(name = "swap")
    public static Object[][] swap() {
        return new Object[][]{
                new Object[]{new int[]{1, 2}, new int[]{2, 1}}
        };
    }

    @Test(dataProvider = "swap")
    public void testSwap(int[] actual, int[] expected) throws Exception {
        Quicksort.swap(actual, 0, 1);

        assertEquals(actual, expected, print(actual) + " || " + print(expected));
    }

    @Test
    public void testStanford() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("resources/week2/QuickSort.txt")));
        int[] giantArray = new int[10000];
        int i = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            giantArray[i] = Integer.parseInt(line);
            i++;
        }
        reader.close();

        assertNotNull(giantArray);
        System.out.print("Performed " + Quicksort.quicksort(giantArray) + " comparisons");
    }
}