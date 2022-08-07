package visualizer;

import visualizer.graph.Graph;

import javax.swing.*;

/**
 * Class representing menu bar that will be added to main frame
 */
public class JMenusBar extends JMenuBar {
    private final JFrame mainFrame;
    private final Graph graph;

    private final JLabel modeLabel;

    public JMenusBar (JFrame mainFrame, Graph graph, JLabel modeLabel) {
        this.mainFrame = mainFrame;
        this.graph = graph;
        this.modeLabel = modeLabel;
    }

    public void addFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem newGraph = new JMenuItem("New");
        newGraph.setName("New");
        newGraph.addActionListener(action -> {
            graph.resetGraph();
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("Exit");
        exit.addActionListener(action -> {
            mainFrame.dispose();
        });

        fileMenu.add(newGraph);
        fileMenu.add(exit);

        fileMenu.setVisible(true);
        add(fileMenu);
    }

    /**
     * Adds a menu containing menu items related to graph editing (e.g. add edge, remove edge)
     */
    public void addModeMenu() {
        JMenu modeMenu = new JMenu("Mode");

        JMenuItem addVertexMode = new JMenuItem("Add a Vertex");
        addVertexMode.setName("Add a Vertex");
        addVertexMode.addActionListener(action -> {
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.NONE);
            Graph.editMode currentMode = Graph.editMode.ADD_VERTEX;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
        });

        JMenuItem addEdgeMode = new JMenuItem("Add an Edge");
        addEdgeMode.setName("Add an Edge");
        addEdgeMode.addActionListener(action -> {
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.NONE);
            Graph.editMode currentMode = Graph.editMode.ADD_EDGE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
        });

        JMenuItem removeVertexMode = new JMenuItem("Remove a Vertex");
        removeVertexMode.setName("Remove a Vertex");
        removeVertexMode.addActionListener(action -> {
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.NONE);
            Graph.editMode currentMode = Graph.editMode.REMOVE_VERTEX;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
        });

        JMenuItem removeEdgeMode = new JMenuItem("Remove an Edge");
        removeEdgeMode.setName("Remove an Edge");
        removeEdgeMode.addActionListener(action -> {
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.NONE);
            Graph.editMode currentMode = Graph.editMode.REMOVE_EDGE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
        });

        JMenuItem noneMode = new JMenuItem("None");
        noneMode.setName("None");
        noneMode.addActionListener(action -> {
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.NONE);
            Graph.editMode currentMode = Graph.editMode.NONE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
        });

        modeMenu.add(addVertexMode);
        modeMenu.add(addEdgeMode);
        modeMenu.add(removeVertexMode);
        modeMenu.add(removeEdgeMode);
        modeMenu.add(noneMode);

        modeMenu.setVisible(true);
        add(modeMenu);
    }

    /**
     * Adds a menu containing menu items related to algorithms
     */
    public void addAlgorithmMenu() {
        JMenu algorithmsMenu = new JMenu("Algorithms");

        JMenuItem dfsAlgorithm = new JMenuItem("Depth-First Search");
        dfsAlgorithm.setName("Depth-First Search");
        dfsAlgorithm.addActionListener(action -> {
            Graph.editMode currentMode = Graph.editMode.NONE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.DFS);
        });

        JMenuItem bfsAlgorithm = new JMenuItem("Breadth-First Search");
        bfsAlgorithm.setName("Breadth-First Search");
        bfsAlgorithm.addActionListener(action -> {
            Graph.editMode currentMode = Graph.editMode.NONE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.BFS);
        });

        JMenuItem dijkstraAlgorithm = new JMenuItem("Dijkstra's algorithm");
        dijkstraAlgorithm.setName("Dijkstra's Algorithm");
        dijkstraAlgorithm.addActionListener(action -> {
            Graph.editMode currentMode = Graph.editMode.NONE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.DIJKSTRA);
        });

        JMenuItem primsAlgorithm = new JMenuItem("Prim's Algorithm");
        primsAlgorithm.setName("Prim's Algorithm");
        primsAlgorithm.addActionListener(action -> {
            Graph.editMode currentMode = Graph.editMode.NONE;
            graph.setCurrentMode(currentMode);
            modeLabel.setText("Current Mode -> " + currentMode.getStrMode());
            graph.setCurrentAlgorithmMode(Graph.algorithmMode.PRIMS);
        });

        algorithmsMenu.add(dfsAlgorithm);
        algorithmsMenu.add(bfsAlgorithm);
        algorithmsMenu.add(dijkstraAlgorithm);
        algorithmsMenu.add(primsAlgorithm);

        algorithmsMenu.setVisible(true);
        add(algorithmsMenu);
    }
}
