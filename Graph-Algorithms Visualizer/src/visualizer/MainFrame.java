package visualizer;

import visualizer.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    void initComponents() {
        JLabel modeLabel = new JLabel("Current Mode -> " + Graph.editMode.ADD_VERTEX.getStrMode());
        modeLabel.setName("Mode");
        modeLabel.setFont(new Font("Courier", Font.BOLD, 16));
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setBounds(550, 10, 250, 16);
        add(modeLabel);
        modeLabel.setVisible(true);

        JLabel algorithmsInfoLabel = new JLabel();
        algorithmsInfoLabel.setName("Display");
        algorithmsInfoLabel.setFont(new Font("Courier", Font.BOLD, 16));
        algorithmsInfoLabel.setForeground(Color.WHITE);
        algorithmsInfoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        algorithmsInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        algorithmsInfoLabel.setBounds(280, 490,300, 50);
        //algorithmsInfoLabel.setBounds((int) (WIDTH - (algorithmsInfoLabel.getText().length() / 2d) * 7) / 2, 510, (int) (algorithmsInfoLabel.getText().length() * 7), 20);
        add(algorithmsInfoLabel);

        Graph graph = new Graph(algorithmsInfoLabel);
        add(graph);

        JMenusBar menusBar = new JMenusBar(this, graph, modeLabel);
        menusBar.addFileMenu();
        menusBar.addModeMenu();
        menusBar.addAlgorithmMenu();
        menusBar.setVisible(true);
        setJMenuBar(menusBar);
    }


}