package io.girum.stanford.week2;

/**
 * Created by Girum Ibssa.
 */
public class Quicksort {

    public static void quicksort(int[] array) {
        quicksort(array, 0, array.length);
    }

    /**
     * Recursive quicksort.
     *
     * @param array array to sort in place
     * @param start start index, inclusive
     * @param end   end index, exclusive
     */
    protected static void quicksort(int[] array, int start, int end) {
        // Base case: an array of size 1 or 0 is considered sorted.
        if ((end - start) < 2) {
            return;
        }

        // Set the pivot to always be the first element of the array.
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
        swap(array, i - 1, start);

        // Sort the left and right halves.
        quicksort(array, start, i - 1);
        quicksort(array, i, end);
    }

    protected static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
