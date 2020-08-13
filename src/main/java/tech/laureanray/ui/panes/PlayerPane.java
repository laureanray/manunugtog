package tech.laureanray.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlayerPane extends HBox{
    private PlayerPane instance;

    private PlayerPane() {
        super();

        Button button = new Button("test");
        this.instance.getChildren().add(button);
    }

    public PlayerPane getInstance() {
        if (instance == null) {
            this.instance = new PlayerPane();
        }
        return instance;
    }
}
