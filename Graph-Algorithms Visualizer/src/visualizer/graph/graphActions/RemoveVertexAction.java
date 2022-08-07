package visualizer.graph.graphActions;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class RemoveVertexAction extends Action{

    public RemoveVertexAction(Graph graph, Deque<Vertex> bufferVertexDeque, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, bufferVertexDeque, adjacencyMap, edgesMap);
    }

    @Override
    void execute(MouseEvent e) {
        Point clickedPoint = new Point(e.getX() + DIFF_X, e.getY() + DIFF_Y);

        Vertex vertexToRemove = null;

        for (Vertex vertex : adjacencyMap.keySet()) {
            if ((clickedPoint.getX() >= vertex.getX() - vertexRadius && clickedPoint.getX() <= vertex.getX() + vertexRadius) && (clickedPoint.getY() >= vertex.getY() - vertexRadius && clickedPoint.getY() <= vertex.getY() + vertexRadius)) {
                vertexToRemove = vertex;
                break;
            }
        }

        ArrayDeque<Edge> edgesBuffer = new ArrayDeque<>();

        if (vertexToRemove != null) {
            for (Map.Entry<Edge, JLabel> entry : edgesMap.entrySet()) {
                Edge edge = entry.getKey();
                JLabel edgeLabel = entry.getValue();
                Vertex startVertex = edge.getStartVertex();
                Vertex endVertex = edge.getEndVertex();

                if (vertexToRemove.containsPoint(startVertex.getPoint()) || vertexToRemove.containsPoint(endVertex.getPoint())) {
                    graph.remove(edge);
                    if (edgeLabel != null) {
                        graph.remove(edgeLabel);
                    }
                    edgesBuffer.offer(edge);
                    //edgesMap.remove(edge);

//                            Edge reversedEdge = edge.getReversedEdge();
//                            JLabel reversedLabel = edgesMap.get(reversedEdge);
//
//                            edgesMap.remove(reversedEdge);
//                            remove(reversedEdge);
//
//                            if (reversedLabel != null) {
//                                remove(reversedLabel);
//                            }
                }

            }

            while (!edgesBuffer.isEmpty()) {
                Edge edge = edgesBuffer.pollFirst();
                edgesMap.remove(edge);
            }

            adjacencyMap.remove(vertexToRemove);
            graph.remove(vertexToRemove);

        }

        graph.repaint();
        graph.revalidate();
    }
}
