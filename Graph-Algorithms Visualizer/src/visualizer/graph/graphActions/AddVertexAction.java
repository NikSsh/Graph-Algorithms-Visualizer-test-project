package visualizer.graph.graphActions;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class AddVertexAction extends Action{


    public AddVertexAction(Graph graph, Deque<Vertex> bufferVertexDeque, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, bufferVertexDeque, adjacencyMap, edgesMap);
    }

    @Override
    void execute(MouseEvent e) {
        if (bufferVertexDeque.isEmpty()) {
            boolean valid = false;
            char vertexId;

            do {
                String input = JOptionPane.showInputDialog(graph, "Enter the Vertex ID(Should be 1 char):", "Vertex", JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    break;
                } else if (input.length() == 1 && !input.isBlank()) {
                    vertexId = input.charAt(0);
                    Vertex vertex = new Vertex(new Point(e.getX() + DIFF_X, e.getY() + DIFF_Y), vertexId);
                    adjacencyMap.putIfAbsent(vertex, new HashMap<>());
                    graph.add(vertex);
                    graph.repaint();
                    graph.revalidate();
                    valid = true;
                } else {
                    JOptionPane.showMessageDialog(graph, "Input must be one character long", "Error. Try again", JOptionPane.ERROR_MESSAGE);
                }
            } while (!valid);
        }
    }
}
