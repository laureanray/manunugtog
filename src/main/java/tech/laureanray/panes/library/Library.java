package tech.laureanray.panes.library;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Library extends HBox {
    private static Library instance;

    private Library() {
    }

    private static void initialize() {
        Text text = new Text("LibraryPane");
        instance.getChildren().add(text);
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
            initialize();
        }

        return instance;
    }
}
