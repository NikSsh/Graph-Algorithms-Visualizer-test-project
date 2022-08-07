package visualizer.graph.graphActions;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Deque;
import java.util.Map;

public class RemoveEdgeAction extends Action{

    public RemoveEdgeAction(Graph graph, Deque<Vertex> bufferVertexDeque, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, bufferVertexDeque, adjacencyMap, edgesMap);
    }

    @Override
    void execute(MouseEvent e) {
        Point clickedPoint = new Point(e.getX() + DIFF_X, e.getY() + DIFF_Y);

        Edge edgeToRemove = null;
        JLabel edgeLabelToRemove = null;

        int absErr = 2;
        for (Map.Entry<Edge, JLabel> entry : edgesMap.entrySet()) {
            Edge edge = entry.getKey();
            JLabel edgeLabel = entry.getValue();
            Vertex startVertex = edge.getStartVertex();
            Vertex endVertex = edge.getEndVertex();


            if (startVertex.getX() < endVertex.getX()) {
                if (startVertex.getY() <= endVertex.getY()) {
                    if ((clickedPoint.getX() >= startVertex.getX() - absErr && clickedPoint.getX() <= endVertex.getX() + absErr) && (clickedPoint.getY() >= startVertex.getY() - absErr && clickedPoint.getY() <= endVertex.getY() + absErr)) {
                        edgeToRemove = edge;
                        edgeLabelToRemove = edgeLabel;
                        break;
                    }
                } else {
                    if ((clickedPoint.getX() >= startVertex.getX() - absErr && clickedPoint.getX() <= endVertex.getX() + absErr) && (clickedPoint.getY() <= startVertex.getY() + absErr && clickedPoint.getY() >= endVertex.getY() - absErr)) {
                        edgeToRemove = edge;
                        edgeLabelToRemove = edgeLabel;
                        break;
                    }
                }
            } else {
                if (startVertex.getY() <= endVertex.getY()) {
                    if ((clickedPoint.getX() <= startVertex.getX() + absErr && clickedPoint.getX() >= endVertex.getX() - absErr) && (clickedPoint.getY() >= startVertex.getY() - absErr && clickedPoint.getY() <= endVertex.getY() + absErr)) {
                        edgeToRemove = edge;
                        edgeLabelToRemove = edgeLabel;
                        break;
                    }
                } else {
                    if ((clickedPoint.getX() <= startVertex.getX() - absErr && clickedPoint.getX() >= endVertex.getX() + absErr) && (clickedPoint.getY() <= startVertex.getY() + absErr && clickedPoint.getY() >= endVertex.getY() - absErr)) {
                        edgeToRemove = edge;
                        edgeLabelToRemove = edgeLabel;
                        break;
                    }
                }
            }
        }



        if (edgeToRemove != null) {
            edgesMap.remove(edgeToRemove);
            graph.remove(edgeToRemove);

            if (edgeLabelToRemove != null) {
                graph.remove(edgeLabelToRemove);
            }

            Edge reversedEdge = edgeToRemove.getReversedEdge();
            JLabel reversedLabel = null;

            for (Map.Entry<Edge, JLabel> entry : edgesMap.entrySet()) {
                Edge edge = entry.getKey();
                if (edge.getName().equals(reversedEdge.getName())) {
                    reversedEdge = edge;
                    reversedLabel = entry.getValue();
                    break;
                }
            }

            edgesMap.remove(reversedEdge);
            graph.remove(reversedEdge);

            if (reversedLabel != null) {
                graph.remove(reversedLabel);
            }
        }

        graph.repaint();
        graph.revalidate();

    }
}
