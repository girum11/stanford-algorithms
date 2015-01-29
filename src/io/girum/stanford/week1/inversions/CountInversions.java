package io.girum.stanford.week1.inversions;

import java.math.BigInteger;
import java.util.Arrays;

public class CountInversions {

    protected static BigInteger countInversions(int[] array) {
        return countInversions(new Array(array, BigInteger.ZERO)).getInversions();
    }

    protected static Array countInversions(Array array) {
        int[] data = array.getData();

        if (data.length <= 1) {
            return array;
        }

        Array leftHalf = countInversions(new Array(Arrays.copyOfRange(data, 0, data.length / 2), BigInteger.ZERO));
        Array rightHalf = countInversions(new Array(Arrays.copyOfRange(data, data.length / 2, data.length),
                BigInteger.ZERO));

        return merge(leftHalf, rightHalf);
    }

    protected static Array merge(Array leftArray, Array rightArray) {
        int[] left = leftArray.getData(), right = rightArray.getData();

        int i = 0, j = 0, k = 0;
        int[] merged = new int[left.length + right.length];
        BigInteger inversions = leftArray.getInversions().add(rightArray.getInversions());

        // Merge the two arrays.
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                merged[k] = left[i];
                i++;
            } else {
                merged[k] = right[j];
                j++;
                inversions = inversions.add(BigInteger.valueOf(left.length - i));
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
