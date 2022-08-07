package visualizer.graph.algorithms;

import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DijkstraAlgorithm extends Algorithm{


    public DijkstraAlgorithm (Graph graph, Map<Vertex, Map<Vertex, Integer>> adjacencyMap, Map<Edge, JLabel> edgesMap) {
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
                String result = dijkstraAlgorithm(startVertex).stream().reduce((accumulator, element) -> accumulator = accumulator + ", " + element).orElse("ERROR");
                return Optional.of(result);
                //algorithmsInfoLabel.setBounds((int) (WIDTH - (algorithmsInfoLabel.getText().length() / 2d) * 16) / 2, 510, (int) (algorithmsInfoLabel.getText().length() * 16), 20);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<String> dijkstraAlgorithm(Vertex startVertex) throws InterruptedException{
        ArrayList<Vertex> visitedVerticesList = new ArrayList<>(edgesMap.size());
        Map<Vertex, Integer> distanceMap = new HashMap<>(edgesMap.size());

        for (Vertex vertex : adjacencyMap.keySet()) {
            distanceMap.put(vertex, Integer.MAX_VALUE);
        }
        distanceMap.put(startVertex, 0);

        class pqVertex {
            final Vertex vertex;
            int distance;

            public pqVertex (Vertex vertex, int distance) {
                this.vertex = vertex;
                this.distance = distance;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public Vertex getVertex() {
                return vertex;
            }
        }

        PriorityQueue<pqVertex> pq = new PriorityQueue<>(Comparator.comparing(pqVertex::getDistance));
        pq.add(new pqVertex(startVertex, 0));

        while (!pq.isEmpty()) {
            pqVertex vertex = pq.poll();
            Vertex current = vertex.getVertex();

            visitedVerticesList.add(current);

            int distance = distanceMap.get(current);
            for (Vertex neighborVertex : adjacencyMap.get(current).keySet()) {
                if (visitedVerticesList.contains(neighborVertex)) {
                    continue;
                }
                Edge edge = edgesMap.keySet().stream().filter(edg -> edg.getStartVertex().equals(current) && edg.getEndVertex().equals(neighborVertex)).findFirst().get();

                int newDist = distance + edge.getWeight();

                if (newDist < distanceMap.get(neighborVertex)) {
                    distanceMap.put(neighborVertex, newDist);
                    pq.add(new pqVertex(neighborVertex, newDist));
                }
            }
        }

        distanceMap.remove(startVertex);

        return distanceMap.entrySet().stream()
                .filter(entry -> entry.getValue() != Integer.MAX_VALUE)
                .map(entry -> entry.getKey().getId() + "=" + entry.getValue())
                .sorted()
                .collect(Collectors.toList());
    }
}
