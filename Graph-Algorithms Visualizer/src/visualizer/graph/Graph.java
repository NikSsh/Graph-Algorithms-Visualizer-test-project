package visualizer.graph;

import visualizer.graph.algorithms.*;
import visualizer.graph.graphActions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.*;

/**
 * class representing graph and graph JPanel over mainframe
 */
public class Graph extends JPanel implements MouseListener {
    private final Map<Edge, JLabel> edgesMap = new HashMap<>();
    private final Map<Vertex, Map<Vertex, Integer>> adjacencyMap = new HashMap<>();
    private final ArrayDeque<Vertex> bufferVertexDeque = new ArrayDeque<>();
    private editMode currentMode = editMode.ADD_VERTEX;
    private algorithmMode currentAlgorithmMode = algorithmMode.NONE;
    private final JLabel algorithmsInfoLabel;

    private final GraphEditor graphEditor = new GraphEditor();

    public Graph(JLabel algorithmsInfoLabel) {
        this.algorithmsInfoLabel= algorithmsInfoLabel;

        setName("Graph");
        setBackground(Color.BLACK);
        setLayout(null);
        addMouseListener(this);
    }

    /**
     * Enum used to track the current edit mode, by default it is 'None'
     */
    public enum editMode {
        ADD_VERTEX("Add a Vertex"),
        ADD_EDGE("Add an Edge"),
        REMOVE_VERTEX("Remove a Vertex"),
        REMOVE_EDGE("Remove an Edge"),
        NONE("None");

        private final String strMode;

        editMode(String strMode) {
            this.strMode = strMode;
        }

        public String getStrMode() {
            return strMode;
        }
    }

    /**
     * Enum used to track the current algorithm mode, by default it is 'None'
     */
    public enum algorithmMode {
        BFS("BFS"),
        DFS("DFS"),
        DIJKSTRA("Dijkstra's algorithm"),
        PRIMS("prims Algorithm"),
        NONE("None");

        private final String strMode;

        algorithmMode(String strMode) {
            this.strMode = strMode;
        }

        public String getStrMode() {
            return strMode;
        }
    }

    public void setCurrentAlgorithmMode(algorithmMode currentAlgorithmMode) {
        this.currentAlgorithmMode = currentAlgorithmMode;
        resetColors();
    }

    public void setCurrentMode(editMode currentMode) {
        this.currentMode = currentMode;
        resetColors();
//        if (!bufferVertexDeque.isEmpty()) {
//            bufferVertexDeque.poll().setActiveColor(false);
//            repaint();
//            revalidate();
//            bufferVertexDeque.clear();
//        }
    }

    /**
     * Used to reset colors after execution of some algorithm
     */
    public void resetColors() {
        for (Vertex vertex : adjacencyMap.keySet()) {
            vertex.setActiveColor(false);
        }

        for (Edge edge : edgesMap.keySet()) {
            edge.setActiveColor(false);
        }

        repaint();
    }

    /**
     * @param e MouseEvent represents data associated with mouse actions. Used to get information about where the cursor was at the time the mouse was clicked.
     *
     * Depending on the selected mode in the menu bar menus, the corresponding actions are performed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (currentAlgorithmMode == algorithmMode.NONE) {
            algorithmsInfoLabel.setVisible(false);
            switch (currentMode) {
                case ADD_VERTEX: {
                    graphEditor.setAction(new AddVertexAction(this, bufferVertexDeque, adjacencyMap, edgesMap));
                    graphEditor.executeAction(e);
                    break;
                }

                case ADD_EDGE: {
                    graphEditor.setAction(new AddEdgeAction(this, bufferVertexDeque, adjacencyMap, edgesMap));
                    graphEditor.executeAction(e);
                    break;
                }

                case REMOVE_EDGE: {
                    graphEditor.setAction(new RemoveEdgeAction(this, bufferVertexDeque, adjacencyMap, edgesMap));
                    graphEditor.executeAction(e);
                    break;
                }

                case REMOVE_VERTEX: {
                    graphEditor.setAction(new RemoveVertexAction(this, bufferVertexDeque, adjacencyMap, edgesMap));
                    graphEditor.executeAction(e);
                    break;
                }
            }
        } else {
            algorithmsInfoLabel.setText("Please choose a starting vertex");
            algorithmsInfoLabel.setVisible(true);
            switch (currentAlgorithmMode) {
                case BFS: {
                    new AlgorithmWorker(algorithmsInfoLabel, e.getPoint(), new BfsAlgorithm(this, adjacencyMap, edgesMap))
                            .execute();
                    break;
                }

                case DFS: {
                    new AlgorithmWorker(algorithmsInfoLabel, e.getPoint(), new DfsAlgorithm(this, adjacencyMap, edgesMap))
                            .execute();

                    break;
                }

                case DIJKSTRA: {
                    new AlgorithmWorker(algorithmsInfoLabel, e.getPoint(), new DijkstraAlgorithm(this, adjacencyMap, edgesMap))
                            .execute();

                    break;
                }

                case PRIMS: {
                    new AlgorithmWorker(algorithmsInfoLabel, e.getPoint(), new PrimsAlgorithm(this, adjacencyMap, edgesMap))
                            .execute();
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * Removes all added vertices and edges from graph JPanel and clears the corresponding collections
     */
    public void resetGraph() {
        for (Map.Entry<Edge, JLabel> entry : edgesMap.entrySet()) {
            remove(entry.getKey());

            JLabel label = entry.getValue();
             if (label != null) {
                remove(label);
            }
        }
        edgesMap.clear();

        for (Vertex vertex : adjacencyMap.keySet()) {
            remove(vertex);
        }
        adjacencyMap.clear();

        algorithmsInfoLabel.setVisible(false);
        repaint();
        revalidate();
    }

    /**
     * @param g The Graphics class is the abstract base class for all graphics contexts that allow an application to draw onto components
     *
     * Used to repaint lines that represent edges
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Map.Entry<Edge, JLabel> entry : edgesMap.entrySet()) {
            if (entry.getValue() != null) {
                Edge edge = entry.getKey();
                Vertex startVertex = edge.getStartVertex();
                Vertex endVertex = edge.getEndVertex();

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(3));
                if (edge.isActiveColor()) {
                    g2.setColor(Color.YELLOW);
                } else {
                    g2.setColor(Color.BLUE);
                }
                Line2D line = new Line2D.Float(
                        startVertex.getX() + 25,
                        startVertex.getY() + 25,
                        endVertex.getX() + 25,
                        endVertex.getY() + 25);
                g2.draw(line);
            }
        }
    }
}
