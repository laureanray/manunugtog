package tech.laureanray.panes.library;

import javafx.scene.control.ScrollPane;
import tech.laureanray.app.ApplicationProperties;

public class LibraryScroll extends ScrollPane {
    private static LibraryScroll instance;

    private LibraryScroll() { }

    public static LibraryScroll getInstance() {
        if (instance == null) {
            instance = new LibraryScroll();

            instance.setWidth(ApplicationProperties.APP_WIDTH);

        }



        return instance;
    }
}
