package io.girum.stanford.week1.inversions;

import java.math.BigInteger;

/**
 * Created by Girum Ibssa.
 */
public class Array {

    private int[] data;
    private BigInteger inversions;

    public Array(int[] data, BigInteger inversions) {
        this.data = data;
        this.inversions = inversions;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public BigInteger getInversions() {
        return inversions;
    }

    public void setInversions(BigInteger inversions) {
        this.inversions = inversions;
    }
}
