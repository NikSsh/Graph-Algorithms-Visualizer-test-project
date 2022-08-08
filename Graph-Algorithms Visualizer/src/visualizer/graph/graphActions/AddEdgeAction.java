package visualizer.graph.graphActions;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Deque;
import java.util.Map;

public class AddEdgeAction extends Action{

    public AddEdgeAction(Graph graph, Deque<Vertex> bufferVertexDeque, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, bufferVertexDeque, adjacencyMap, edgesMap);
    }

    @Override
    void execute(MouseEvent e) {
        Point clickedPoint = new Point(e.getX() + DIFF_X, e.getY() + DIFF_Y);
        if (bufferVertexDeque.isEmpty()) {
            for (Vertex vertex : adjacencyMap.keySet()) {
                if (vertex.containsPoint(clickedPoint)) {
                    vertex.setActiveColor(true);
                    graph.repaint();
                    graph.revalidate();
                    bufferVertexDeque.add(vertex);
                    break;
                }
            }
        } else {
            for (Vertex vertex : adjacencyMap.keySet()) {
                if (vertex.containsPoint(clickedPoint)) {
                    bufferVertexDeque.add(vertex);
                    break;
                }
            }

            if (bufferVertexDeque.size() == 2) {
                Vertex startVertex = bufferVertexDeque.pollFirst();
                Vertex endVertex = bufferVertexDeque.pollFirst();

                boolean valid = false;
                int weight;

                do {
                    String input = JOptionPane.showInputDialog(graph, "Input edge weight(integer):", "Edge", JOptionPane.QUESTION_MESSAGE);

                    if (input == null) {
                        startVertex.setActiveColor(false);
                        graph.repaint();
                        graph.revalidate();
                        break;
                    } else if (!input.isBlank()) {
                        try {
                            weight = Integer.parseInt(input);
                            startVertex.setActiveColor(false);
                            Edge edge = new Edge(startVertex, endVertex, weight);
                            Edge reversedEdge = edge.getReversedEdge();
                            JLabel edgeLabel = edge.getLabel();
                            graph.add(edgeLabel);
                            graph.repaint();
                            graph.revalidate();
                            graph.add(edge);
                            graph.add(reversedEdge);
                            edgesMap.putIfAbsent(edge, edgeLabel);
                            edgesMap.putIfAbsent(reversedEdge, null);
                            adjacencyMap.get(startVertex).putIfAbsent(endVertex, weight);
                            adjacencyMap.get(endVertex).putIfAbsent(startVertex, weight);
                            valid = true;

                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(graph, "Input must be a number", "Error. Try again", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } while (!valid);
                bufferVertexDeque.clear();
            } else {
                if (!bufferVertexDeque.isEmpty()) {
                    bufferVertexDeque.poll().setActiveColor(false);
                    graph.repaint();
                    bufferVertexDeque.clear();
                }
            }
        }
    }
}
