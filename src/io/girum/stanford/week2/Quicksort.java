package io.girum.stanford.week2;

import java.math.BigInteger;

/**
 * Created by Girum Ibssa.
 */
public class Quicksort {

    public static BigInteger quicksort(int[] array) {
        return quicksort(array, 0, array.length);
    }

    /**
     * Recursive quicksort.
     *
     * @param array array to sort in place
     * @param start start index, inclusive
     * @param end   end index, exclusive
     */
    protected static BigInteger quicksort(int[] array, int start, int end) {
        // Base case: an array of size 1 or 0 is considered sorted.
        if ((end - start) < 2) {
            return BigInteger.ZERO;
        }

        BigInteger comparisons = BigInteger.valueOf(end - start - 1);

        // Set the pivot to always be the first element of the array.
        //noinspection UnnecessaryLocalVariable
        swap(array, start, medianOfThree(array, start, end));
        int pivot = start;

        // Set boundaries for the "left partition" and the "right partition".
        int i = start + 1;
        int j = start + 1;

        // Partition around the pivot.
        for (; j < end; j++) {

            if (array[j] < array[pivot]) {
                swap(array, i, j);
                i++;
            }
        }

        // Put the pivot in its correct place.
        swap(array, i - 1, pivot);

        // Sort the left and right halves.
        comparisons = comparisons.add(quicksort(array, start, i - 1));
        comparisons = comparisons.add(quicksort(array, i, end));

        return comparisons;
    }

    protected static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    protected static int medianOfThree(int[] array, int start, int end) {
        int first = array[start];
        int middle = array[(end - 1 - start) / 2];
        int last = array[end - 1];

        int max = Math.max(Math.max(first, middle), last);
        int min = Math.min(Math.min(first, middle), last);
        int median = first^middle^last^max^min;

        if (median == first) {
            return start;
        } else if (median == middle) {
            return (end - 1 - start) / 2;
        } else {
            return end - 1;
        }
    }

}
