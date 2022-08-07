package visualizer.graph;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JPanel {
    private static final int VERTEX_DIM = 50;
    private final Point point;
    private boolean activeColor;
    private final char id;


    public Vertex(Point point, char id) {
        this.point = point;
        this.id = id;

        setName("Vertex " + id);
        setSize(new Dimension(VERTEX_DIM, VERTEX_DIM));
        setBackground(Color.BLACK);

        JLabel vertexIdLabel = new JLabel("" + id);
        vertexIdLabel.setName("VertexLabel " + id);
        vertexIdLabel.setFont(new Font("Courier", Font.ITALIC, 30));
        vertexIdLabel.setForeground(Color.CYAN);
        vertexIdLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(vertexIdLabel);
    }

    public void setActiveColor(boolean activeColor) {
        this.activeColor = activeColor;
    }

    public char getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!activeColor) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.YELLOW);
        }
        g.fillOval(0, 0, VERTEX_DIM - 1, VERTEX_DIM - 1);
        setLocation(point);
    }

    public boolean containsPoint(Point point) {
        return ((point.getX() >= this.getX() - VERTEX_DIM + 10 && point.getX() <= this.getX() + VERTEX_DIM + 10) && (point.getY() >= this.getY() - VERTEX_DIM + 10 && point.getY() <= this.getY() + VERTEX_DIM + 10));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (hashCode() != vertex.hashCode()) return false;
        if (activeColor != vertex.activeColor) return false;
        if (id != vertex.id) return false;
        return point.equals(vertex.point);
    }

    @Override
    public int hashCode() {
        int result = point.hashCode();
        result = 31 * result;
        result = 31 * result + (int) id;
        return result;
    }
}
