package org.boy.aoc.twentytwentythree.exercises;

import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class day25 extends SolutionTemplate {

    public String pointOne(String input) {
        Graph<String, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);

        for(String line: input.split("\n")) {
            String[] parts = line.split(": | ");
            g.addVertex(parts[0]);

            for(int i = 1; i < parts.length; i++) {
                g.addVertex(parts[i]);
                g.addEdge(parts[0], parts[i]);
            }
        }

        StoerWagnerMinimumCut<String, DefaultEdge> c = new StoerWagnerMinimumCut<>(g);

        int leftCut = c.minCut().size();
        int rightCut = g.vertexSet().size() - leftCut;

        return String.valueOf(leftCut * rightCut);
    }


    public String pointTwo(String input) {
        return "Free star!";
    }

}