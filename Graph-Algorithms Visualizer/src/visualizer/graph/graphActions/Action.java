package visualizer.graph.graphActions;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Deque;
import java.util.Map;

abstract class Action {
    protected static final int DIFF_X = -25, DIFF_Y = -25;
    protected static final int vertexRadius = 25;
    protected Graph graph;
    protected final Deque<Vertex> bufferVertexDeque;
    protected final Map<Vertex, Map<Vertex, Integer>> adjacencyMap;
    protected final Map<Edge, JLabel> edgesMap;

    public Action(Graph graph, Deque<Vertex> bufferVertexDeque, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        this.graph = graph;
        this.bufferVertexDeque = bufferVertexDeque;
        this.adjacencyMap = adjacencyMap;
        this.edgesMap = edgesMap;
    }

    abstract void execute(MouseEvent e);
}
