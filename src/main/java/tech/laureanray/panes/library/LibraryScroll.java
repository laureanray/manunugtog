package tech.laureanray.panes.library;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LibraryScroll extends ScrollPane {
    private static LibraryScroll instance;

    private LibraryScroll() {
        super();
        VBox box = new VBox();

        for (int i = 0; i < 50; i++) {
            box.getChildren().add(new Button("Button Test "));
        }

        this.getChildren().add(box);
    }

    public static LibraryScroll getInstance() {
        if (instance == null) {
            instance = new LibraryScroll();
        }

        return instance;
    }
}
