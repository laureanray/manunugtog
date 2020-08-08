package tech.laureanray.panes.library;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Library extends VBox {
    private static Library instance;

    private Library() {
    }

    private static void initialize() {
        Text text = new Text("LibraryPane");
        LibraryScroll scroll = LibraryScroll.getInstance();
        instance.getChildren().add(text);
        instance.getChildren().add(scroll);
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
            initialize();
        }

        return instance;
    }
}
