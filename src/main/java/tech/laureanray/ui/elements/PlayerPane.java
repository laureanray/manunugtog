package tech.laureanray.ui.elements;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerPane extends VBox {
    private static PlayerPane instance;

    private PlayerPane() {
        super();
        this.init();
        this.setId("player");
    }

    private void init() {
        Text artist = new Text("Artist");
        Text title = new Text("Title");
        ProgressBar trackProgress = new ProgressBar(0);
        trackProgress.setProgress(0.25f);
        trackProgress.setPrefWidth(400);
        trackProgress.setPrefHeight(10);
        HBox box = new HBox();

        Button prevButton = new Button("Prev");
        Button playButton = new Button("Play");
        Button nextButton = new Button("Next");

        box.getChildren().addAll(prevButton, playButton, nextButton);

        this.getChildren().addAll(artist, title, trackProgress, box);

    }

    public static PlayerPane getInstance() {
        if (instance == null) {
            instance = new PlayerPane();

        }
        return instance;
    }
}
