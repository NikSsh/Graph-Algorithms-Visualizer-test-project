package visualizer.graph;

import javax.swing.*;
import java.awt.*;

public class Edge extends JComponent {
    private final Vertex startVertex, endVertex;
    private final int weight;
    private boolean activeColor;

    public Edge(Vertex startVertex, Vertex endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        setName(String.format("Edge <%s -> %s>", startVertex.getId(), endVertex.getId()));

        setVisible(true);
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public Edge getReversedEdge() {
        return new Edge(endVertex, startVertex, weight);
    }

    public void setActiveColor(boolean activeColor) {
        this.activeColor = activeColor;
    }

    public boolean isActiveColor() {
        return activeColor;
    }

    public JLabel getLabel() {
        JLabel label = new JLabel(String.valueOf(weight));
        Font font = new Font("Courier", Font.BOLD, 15);
        label.setName(String.format("EdgeLabel <%s -> %s>", startVertex.getId(), endVertex.getId()));
        System.out.println(getName());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        int labelWidth = label.getFontMetrics(font).stringWidth(label.getText()) + 8;
        int labelHeight = label.getFontMetrics(font).getHeight();

        boolean needCorrect = Math.abs(startVertex.getX() - endVertex.getX()) > Math.abs(startVertex.getY() - endVertex.getY());
        label.setBounds((startVertex.getX() + 25 + endVertex.getX() + 25) / 2 + (needCorrect ? -5 : 15),
                (startVertex.getY() + 25 + endVertex.getY() + 25) / 2 + (needCorrect ? 20 : 10),
                labelWidth, labelHeight);

        if (weight == 0) {
            label.setVisible(false);
        }
        return label;
    }

    public boolean containsVertices (Vertex vertex1, Vertex vertex2) {
        return (vertex1.equals(startVertex) && vertex2.equals(endVertex)) || (vertex1.equals(endVertex) && vertex2.equals(startVertex));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (weight != edge.weight) return false;
        if (!startVertex.equals(edge.startVertex)) return false;
        return endVertex.equals(edge.endVertex);
    }

    @Override
    public int hashCode() {
        int result = startVertex.hashCode();
        result = 31 * result + endVertex.hashCode();
        result = 31 * result + weight;
        return result;
    }
}
