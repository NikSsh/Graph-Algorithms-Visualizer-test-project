package visualizer.graph.algorithms;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class BfsAlgorithm extends Algorithm{

    public BfsAlgorithm (Graph graph, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, adjacencyMap, edgesMap);
    }

    @Override
    Optional<String> execute(Point clickedPoint) {
        return bfsAlgorithm(clickedPoint);
    }

    public Optional<String> bfsAlgorithm(Point clickedPoint) {
        Vertex startVertex = null;
        for (Vertex vertex : adjacencyMap.keySet()) {
            if ((clickedPoint.getX() >= vertex.getX() - 50 && clickedPoint.getX() <= vertex.getX() + 50) && (clickedPoint.getY() >= vertex.getY() - 50 && clickedPoint.getY() <= vertex.getY() + 50)) {
                startVertex = vertex;
                break;
            }
        }

        if (startVertex != null) {
            java.util.Queue<Vertex> verticesQueue = new ArrayDeque<>();
            verticesQueue.offer(startVertex);
            ArrayList<Vertex> visitedVertices = new ArrayList<>(edgesMap.size());
            visitedVertices.add(startVertex);

            StringBuilder pathBuilder = new StringBuilder();
            pathBuilder.append("BFS : ");

            try {
                while (!verticesQueue.isEmpty()) {
                    Vertex current = verticesQueue.poll();
                    current.setActiveColor(true);
                    graph.repaint();

                    Thread.sleep(500);
                    pathBuilder.append(current.getId()).append(" -> ");

                    List<Vertex> vertexAdjacencyList = adjacencyMap.get(current).entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
                    for (Vertex neighborVertex : vertexAdjacencyList) {
                        if (!visitedVertices.contains(neighborVertex)) {
                            verticesQueue.offer(neighborVertex);
                            visitedVertices.add(neighborVertex);
                            for (Edge edge : edgesMap.keySet()) {
                                if (edge.containsVertices(current, neighborVertex)) {
                                    edge.setActiveColor(true);
                                    graph.repaint();
                                }
                            }
                            neighborVertex.setActiveColor(true);
                            neighborVertex.repaint();
                            Thread.sleep(500);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Optional.of(pathBuilder.toString().replaceAll("\\s->\\s$", ""));
        }

        return Optional.empty();
    }
}
