package io.girum.stanford.week1.inversions;

/**
 * Created by Girum Ibssa.
 */
public class Array {

    private int[] data;
    private int inversions;

    public Array(int[] data, int inversions) {
        this.data = data;
        this.inversions = inversions;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getInversions() {
        return inversions;
    }

    public void setInversions(int inversions) {
        this.inversions = inversions;
    }
}
