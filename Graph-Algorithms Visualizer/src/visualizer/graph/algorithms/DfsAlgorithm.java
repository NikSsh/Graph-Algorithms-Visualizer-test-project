package visualizer.graph.algorithms;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DfsAlgorithm extends Algorithm{
    public DfsAlgorithm (Graph graph, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, adjacencyMap, edgesMap);
    }

    @Override
    Optional<String> execute(Point clickedPoint) {
        return dfsAlgorithm(clickedPoint);
    }

    private Optional<String> dfsAlgorithm(Point clickedPoint) {
        Vertex startVertex = null;
        for (Vertex vertex : adjacencyMap.keySet()) {
            if ((clickedPoint.getX() >= vertex.getX() - 50 && clickedPoint.getX() <= vertex.getX() + 50) && (clickedPoint.getY() >= vertex.getY() - 50 && clickedPoint.getY() <= vertex.getY() + 50)) {
                startVertex = vertex;
                break;
            }
        }

        if (startVertex != null) {
            ArrayList<Vertex> visitedVertices = new ArrayList<>(edgesMap.size());

            StringBuilder pathBuilder = new StringBuilder();
            pathBuilder.append("DFS : ");

            try {
                dfsVisit(visitedVertices, startVertex, pathBuilder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Optional.of(pathBuilder.toString().replaceAll("\\s->\\s$", ""));
        }

        return Optional.empty();
    }

    private void dfsVisit(ArrayList<Vertex> visitedVertices, Vertex chosenVertex, StringBuilder pathBuilder) throws InterruptedException{
        if (visitedVertices.contains(chosenVertex)) {
            return;
        } else {
            visitedVertices.add(chosenVertex);
            chosenVertex.setActiveColor(true);
            graph.repaint();
            pathBuilder.append(chosenVertex.getId()).append(" -> ");
            Thread.sleep(500);
        }

        List<Vertex> adjacencyVertexList = adjacencyMap.get(chosenVertex).entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
        for (Vertex vertex : adjacencyVertexList) {
            if (!visitedVertices.contains(vertex)) {
                for (Edge edge : edgesMap.keySet()) {
                    if (edge.containsVertices(chosenVertex, vertex)) {
                        edge.setActiveColor(true);
                        graph.repaint();
                    }
                }
                dfsVisit(visitedVertices, vertex, pathBuilder);
            }
        }
    }
}
