package io.girum.stanford.week3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Undirected graph represented by an adjacency list
 * .
 * Created by Girum Ibssa.
 */
public class UndirectedGraph {

    protected List<List<Integer>> adjacencies;
    protected List<List<Integer>> contracted;
    protected int size;

    public UndirectedGraph(int size) {

        this.size = size;

        // Initialize the adjacency list.
        adjacencies = new ArrayList<List<Integer>>(size + 1);
        contracted = new ArrayList<List<Integer>>(size + 1);
        for (int i = 0; i <= size; i++) {
            adjacencies.add(new ArrayList<Integer>());
            contracted.add(new ArrayList<Integer>());
        }

    }

    public void addAdjacency(Integer v1, Integer v2) {
        adjacencies.get(v1).add(v2);
    }

    public int getMinCut() {

        int minCutSoFar = Integer.MAX_VALUE;

        // Repeatedly run the random contraction algorithm N^2 times.
        for (int i = 0; i < size * size; i++) {
            int minCut = randomContraction();

            // Remember the size of the "minimum" cut you've seen so far.
            if (minCut < minCutSoFar) {
                minCutSoFar = minCut;
            }
        }

        return minCutSoFar;
    }

    protected int randomContraction() {
        Random random = new Random();

        UndirectedGraph copy = this.copy();

        while (copy.size > 2) {
            copy.contract(random.nextInt(size), random.nextInt(size));
        }

        return copy.adjacencies.get(1).size();
    }

    public int size() {
        return size;
    }

    /**
     * Collapses two vertices into one "supervertex". Assume v1 is the "winner" node.
     */
    public void contract(int v1, int v2) {
        List<Integer> merged = new ArrayList<Integer>();

        // Mark v1 as being contracted with v2 and vice versa.
        contracted.get(v1).add(v2);
        contracted.get(v2).add(v1);

        // Add all of v1's adjacencies into the merged list. Puts loops in for now.
        for (Integer v1Adjacency : adjacencies.get(v1)) {
            merged.add(v1Adjacency);
        }

        // Add all of v2's adjacencies into the merged list too.
        for (Integer v2Adjacency : adjacencies.get(v2)) {
            merged.add(v2Adjacency);
        }

        // Yank all of those "contracted" nodes from the merged list
        // (aka remove loops).
        merged.removeAll(contracted.get(v1));
        merged.removeAll(contracted.get(v2));

        // Set the existing adjacency lists for v1 and v2 to both be the new, merged list.
        adjacencies.set(v1, merged);
        adjacencies.set(v2, merged);

        // Set the other contracted vertices in the set to have the new merged list.
        for (Integer contracted : this.contracted.get(v1)) {
            adjacencies.set(contracted, merged);
        }
        for (Integer contracted : this.contracted.get(v2)) {
            adjacencies.set(contracted, merged);
        }

        // Decrement the size of the graph when you're done.
        size--;
    }

    protected UndirectedGraph copy() {
        UndirectedGraph clone = new UndirectedGraph(size);
        clone.adjacencies = new ArrayList<List<Integer>>(this.adjacencies);
        clone.contracted = new ArrayList<List<Integer>>(this.contracted);
        clone.size = this.size;

        return clone;
    }
}
