package visualizer.graph.algorithms;

import javax.swing.*;
import java.awt.*;

public class AlgorithmWorker extends SwingWorker<String, Object> {
    private final JLabel algorithmsInfoLabel;
    private final Algorithm concreteAlgorithm;
    private final Point clickedPoint;

    public AlgorithmWorker(JLabel algorithmsInfoLabel, Point clickedPoint, Algorithm concreteAlgorithm) {
        this.algorithmsInfoLabel = algorithmsInfoLabel;
        this.concreteAlgorithm = concreteAlgorithm;
        this.clickedPoint = clickedPoint;
    }

    @Override
    protected String doInBackground() {
        algorithmsInfoLabel.setText("Please wait...");
        String result = concreteAlgorithm.execute(clickedPoint).orElse("ERROR");
        algorithmsInfoLabel.setText(result);
        return "";
    }
}
