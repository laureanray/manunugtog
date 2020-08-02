package tech.laureanray.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class NowPlayingPane extends HBox{
    private NowPlayingPane instance;

    private NowPlayingPane() {
        super();

        Button button = new Button("test");
        this.instance.getChildren().add(button);
    }

    public NowPlayingPane getInstance() {
        if (instance == null) {
            this.instance = new NowPlayingPane();
        }
        return instance;
    }
}
