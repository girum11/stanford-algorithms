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
            adjacencies.add(new LinkedList<Integer>());

            // Create a Vertex Set that contains only this node, at first.
            Set<Integer> set = new HashSet<Integer>(size);
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

        Random random = new Random();

        // Repeatedly run the random contraction algorithm N^2 times.
        for (int i = 0; i < size * size; i++) {
            random.setSeed(i);

            List<Integer> cut = randomContraction(random);
            int minCut = cut.size();

            // Remember the size of the "minimum" cut you've seen so far.
            if (minCut < minCutSoFar) {
                minCutSoFar = minCut;
            }
        }

        return minCutSoFar;
    }

    protected List<Integer> randomContraction(Random random) {
        UndirectedGraph copy = this.copy();

        while (copy.size > 2) {

            // Randomly pick two vertices to merge together.
            int random1 = random.nextInt(size) + 1;
            int random2 = random.nextInt(size) + 1;

            // Protect against picking random1 and random2 as the same value.
            // Also protect against trying to merge two vertices that are already merged.
            while (random2 == random1 || copy.vertexSets.get(random1).contains(random2)) {
                random2 = random.nextInt(size) + 1;
            }

            // Merge the two vertices.
            copy.contract(random1, random2);
        }

        return copy.adjacencies.get(1);
    }

    public int size() {
        return size;
    }

    /**
     * Collapses two vertices into one "supervertex". Assume v1 is the "winner" node.
     */
    public void contract(int v1, int v2) {
        List<Integer> merged = new LinkedList<Integer>();

        // First, merge the two Vertex Sets together.
        Set<Integer> mergedVertexSet = new HashSet<Integer>(vertexSets.get(v1).size() + vertexSets.get(v2).size());
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
