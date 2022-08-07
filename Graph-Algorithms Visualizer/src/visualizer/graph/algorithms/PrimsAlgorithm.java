package visualizer.graph.algorithms;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PrimsAlgorithm extends Algorithm{
    public PrimsAlgorithm (Graph graph, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
        super(graph, adjacencyMap, edgesMap);
    }

    @Override
    Optional<String> execute(Point clickedPoint) {
        try {
            Vertex startVertex = null;
            for (Vertex vertex : adjacencyMap.keySet()) {
                if ((clickedPoint.getX() >= vertex.getX() - 50 && clickedPoint.getX() <= vertex.getX() + 50) && (clickedPoint.getY() >= vertex.getY() - 50 && clickedPoint.getY() <= vertex.getY() + 50)) {
                    startVertex = vertex;
                    break;
                }
            }

            if (startVertex != null) {
                Thread.sleep(1000);
                startVertex.setActiveColor(true);
                graph.repaint();
                String result = primsAlgorithm(startVertex).stream().reduce((accumulator, element) -> accumulator = accumulator + ", " + element).orElse("ERROR");
                return Optional.of(result);
                //algorithmsInfoLabel.setBounds((int) (WIDTH - (algorithmsInfoLabel.getText().length() / 2d) * 16) / 2, 510, (int) (algorithmsInfoLabel.getText().length() * 16), 20);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }

    private List<String> primsAlgorithm(Vertex startVertex) {

        class pqEdge {
            final Vertex startVertex, endVertex;
            int distance;

            public pqEdge (Vertex startVertex, Vertex endVertex, int distance) {
                this.startVertex = startVertex;
                this.endVertex = endVertex;
                this.distance = distance;
            }

            public Vertex getStartVertex() {
                return startVertex;
            }

            public Vertex getEndVertex() {
                return endVertex;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }
        }

        Map<Vertex, Integer> distanceMap = new HashMap<>();

        for (Vertex vertex : adjacencyMap.keySet()) {
            distanceMap.put(vertex, Integer.MAX_VALUE);
        }
        distanceMap.put(startVertex, 0);

        PriorityQueue<pqEdge> pq = new PriorityQueue<>(Comparator.comparing(pqEdge::getDistance));
        pq.add(new pqEdge(null, startVertex, 0));
        int mstCost = 0;

        ArrayList<Vertex> visited = new ArrayList<>();

        List<pqEdge> edgesList = new LinkedList<>();
        while (!pq.isEmpty()) {
            pqEdge edge = pq.poll();
            Vertex fromVertex = edge.getStartVertex();
            Vertex toVertex = edge.getEndVertex();

            if (visited.contains(toVertex)) continue;

            if (fromVertex != null) {
                mstCost += edge.getDistance();
                edgesList.add(edge);
                visited.add(fromVertex);
                visited.add(toVertex);
                fromVertex.setActiveColor(true);

                for (Edge edg : edgesMap.keySet()) {
                    if (edg.containsVertices(fromVertex, toVertex)) {
                        edg.setActiveColor(true);
                    }
                }
                graph.repaint();
                toVertex.setActiveColor(true);
                toVertex.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (Map.Entry<Vertex, Integer> entry : adjacencyMap.get(toVertex).entrySet()) {
                int weight = entry.getValue();
                Vertex neighborVertex = entry.getKey();

                if (visited.contains(neighborVertex)) {
                    continue;
                }

                if (weight < distanceMap.get(neighborVertex)) {
                    pq.add(new pqEdge(toVertex, neighborVertex, weight));
                    distanceMap.put(neighborVertex, weight);
                }
            }
        }


        return edgesList.stream()
                .map(pqEdge -> pqEdge.getEndVertex().getId() + "=" + pqEdge.getStartVertex().getId())
                .sorted()
                .collect(Collectors.toList());
    }
}
