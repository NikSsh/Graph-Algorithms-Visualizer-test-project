package visualizer.graph.algorithms;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Optional;

abstract class Algorithm {
    protected Graph graph;
    protected final Map<Vertex, Map<Vertex, Integer>> adjacencyMap;
    protected final Map<Edge, JLabel> edgesMap;

    public Algorithm (Graph graph, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        this.graph = graph;
        this.adjacencyMap = adjacencyMap;
        this.edgesMap = edgesMap;
    }

    abstract Optional<String> execute(Point clickedPoint);
}
