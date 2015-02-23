package io.girum.stanford.week3;

import io.girum.stanford.week3.model.UndirectedGraph;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MinCutTest {

    @Test
    public void testGraphContraction() throws Exception {
        UndirectedGraph graph = new UndirectedGraph(5);

        graph.addAdjacency(1, 2);
        graph.addAdjacency(1, 5);
        graph.addAdjacency(1, 3);
        graph.addAdjacency(2, 1);
        graph.addAdjacency(2, 3);
        graph.addAdjacency(3, 2);
        graph.addAdjacency(3, 4);
        graph.addAdjacency(3, 5);
        graph.addAdjacency(3, 1);
        graph.addAdjacency(4, 3);
        graph.addAdjacency(4, 5);
        graph.addAdjacency(5, 1);
        graph.addAdjacency(5, 4);
        graph.addAdjacency(5, 3);

        assertEquals(graph.size(), 5);

        graph.contract(2, 3);
        assertEquals(graph.size(), 4);

        graph.contract(1, 5);
        assertEquals(graph.size(), 3);

        graph.contract(2, 4);
        assertEquals(graph.size(), 2);
    }

    @Test
    public void testMinCut() throws Exception {
        UndirectedGraph graph = new UndirectedGraph(5);

        graph.addAdjacency(1, 2);
        graph.addAdjacency(1, 5);
        graph.addAdjacency(1, 3);
        graph.addAdjacency(2, 1);
        graph.addAdjacency(2, 3);
        graph.addAdjacency(3, 2);
        graph.addAdjacency(3, 4);
        graph.addAdjacency(3, 5);
        graph.addAdjacency(3, 1);
        graph.addAdjacency(4, 3);
        graph.addAdjacency(4, 5);
        graph.addAdjacency(5, 1);
        graph.addAdjacency(5, 4);
        graph.addAdjacency(5, 3);

        assertEquals(graph.getMinCut(), 3);
    }

    @Test
    public void testStanford() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("resources/week3/kargerMinCut.txt")));
        UndirectedGraph graph = new UndirectedGraph(200);

        String line;
        while ((line = reader.readLine()) != null) {

            String[] keyValueSplit = line.split("\t");
            int key = Integer.parseInt(keyValueSplit[0]);

            for (int i = 1; i < keyValueSplit.length; i++) {
                graph.addAdjacency(key, Integer.parseInt(keyValueSplit[i]));
            }
        }
        reader.close();

        assertNotNull(graph);
        System.out.println("Min cut of graph was " + MinCut.findMinCut(graph) + ".");
    }
}