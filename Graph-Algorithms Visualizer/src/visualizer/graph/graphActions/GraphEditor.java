package visualizer.graph.graphActions;

import java.awt.event.MouseEvent;

public class GraphEditor {
    private Action action;

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void executeAction(MouseEvent event) {
        action.execute(event);
    }
}
