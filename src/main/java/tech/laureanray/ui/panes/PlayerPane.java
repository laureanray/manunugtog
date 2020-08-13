package tech.laureanray.ui.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PlayerPane extends VBox {
    private static PlayerPane instance;

    private PlayerPane() {
        super();
        this.init();
        this.setId("player");
    }

    private void init() {
        Button button = new Button("test");
        this.getChildren().add(button);
    }

    public static PlayerPane getInstance() {
        if (instance == null) {
            instance = new PlayerPane();

        }
        return instance;
    }
}
