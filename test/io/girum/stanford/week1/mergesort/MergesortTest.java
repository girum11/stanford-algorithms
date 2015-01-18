package io.girum.stanford.week1.mergesort;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MergesortTest {

    @Test
    public void testMergesort() throws Exception {

        int[] array = new int[] {10, 9, 8, 8, 7, 1, 2, 3};

        assertEquals(Mergesort.mergesort(array), new int[] {1, 2, 3, 7, 8, 8, 9, 10});
    }
}