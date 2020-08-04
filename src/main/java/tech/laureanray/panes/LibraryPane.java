package tech.laureanray.panes;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LibraryPane extends HBox {
    private static LibraryPane instance;

    private LibraryPane() {
    }

    private static void initialize() {
        Text text = new Text("LibraryPane");
        instance.getChildren().add(text);
    }

    public static LibraryPane getInstance() {
        if (instance == null) {
            instance = new LibraryPane();
            initialize();
        }

        return instance;
    }
}
