package tech.laureanray.panes.library;

import javafx.scene.control.ScrollPane;

public class LibraryScroll extends ScrollPane {
    private static LibraryScroll instance;

    private LibraryScroll() { }

    public static LibraryScroll getInstance() {
        if (instance == null) {
            instance = new LibraryScroll();
        }

        return instance;
    }
}
