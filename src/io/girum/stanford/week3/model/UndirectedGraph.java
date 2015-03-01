package io.girum.stanford.week3.model;

import java.util.*;

/**
 * Undirected graph represented by an adjacency list
 * .
 * Created by Girum Ibssa.
 */
public class UndirectedGraph {

    protected List<List<Integer>> adjacencies;
    protected List<Set<Integer>> vertexSets;
    protected int size;


    public UndirectedGraph(int size) {

        this.size = size;

        // Initialize the adjacency list.
        adjacencies = new ArrayList<List<Integer>>(size + 1);
        vertexSets = new ArrayList<Set<Integer>>(size + 1);
        for (int i = 0; i <= size; i++) {
            adjacencies.add(new ArrayList<Integer>());

            // Create a Vertex Set that contains only this node, at first.
            Set<Integer> set = new HashSet<Integer>();
            if (i != 0) {
                set.add(i);
            }
            vertexSets.add(set);
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

        // First, merge the two Vertex Sets together.
        Set<Integer> mergedVertexSet = new HashSet<Integer>();
        mergedVertexSet.addAll(vertexSets.get(v1));
        mergedVertexSet.addAll(vertexSets.get(v2));

        // Then apply that merged Vertex Set to all vertices belonging to that set.
        for (Integer mergedVertex : mergedVertexSet) {
            vertexSets.set(mergedVertex, mergedVertexSet);
        }

        // Now actually merge v1 and v2. No self-loops.
        for (Integer vertex : adjacencies.get(v1)) {
            if (!mergedVertexSet.contains(vertex)) {
                merged.add(vertex);
            }
        }
        for (Integer vertex : adjacencies.get(v2)) {
            if (!mergedVertexSet.contains(vertex)) {
                merged.add(vertex);
            }
        }

        // Lastly, set all of the vertices in the Vertex Set to have the merged
        // adjacency list.
        for (Integer mergedVertex : mergedVertexSet) {
            adjacencies.set(mergedVertex, merged);
        }

        // Decrement the size of the whole graph when you're done.
        size--;
    }

    protected UndirectedGraph copy() {
        UndirectedGraph clone = new UndirectedGraph(size);
        clone.adjacencies = new ArrayList<List<Integer>>(this.adjacencies);
        clone.vertexSets = new ArrayList<Set<Integer>>(this.vertexSets);
        clone.size = this.size;

        return clone;
    }
}
