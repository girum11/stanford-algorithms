package io.girum.stanford.week1.inversions;

import java.util.Arrays;

public class CountInversions {

    protected static int countInversions(int[] array) {
        return countInversions(new Array(array, 0)).getInversions();
    }

    protected static Array countInversions(Array array) {
        int[] data = array.getData();

        if (data.length <= 1) {
            return array;
        }

        Array leftHalf = countInversions(new Array(Arrays.copyOfRange(data, 0, data.length / 2), 0));
        Array rightHalf = countInversions(new Array(Arrays.copyOfRange(data, data.length / 2, data.length), 0));

        return merge(leftHalf, rightHalf);
    }

    protected static Array merge(Array leftArray, Array rightArray) {
        int[] left = leftArray.getData(), right = rightArray.getData();

        int i = 0, j = 0, k = 0;
        int[] merged = new int[left.length + right.length];
        int inversions = leftArray.getInversions() + rightArray.getInversions();

        // Merge the two arrays.
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                merged[k] = left[i];
                i++;
            } else {
                merged[k] = right[j];
                j++;
                inversions += (left.length - i);
            }

            k++;
        }

        // Copy over any remaining elements.
        while (i < left.length) {
            merged[k] = left[i];
            i++;
            k++;
        }

        // Copy over any remaining elements.
        while (j < right.length) {
            merged[k] = right[j];
            j++;
            k++;
        }

        return new Array(merged, inversions);
    }


}
