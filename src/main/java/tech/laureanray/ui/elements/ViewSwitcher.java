package tech.laureanray.ui.elements;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tech.laureanray.ui.elements.library.AlbumsPane;
import tech.laureanray.ui.elements.library.TracksPane;
import tech.laureanray.ui.scenes.MainScene;

public class ViewSwitcher extends HBox  {
    private static ViewSwitcher instance;

    private ViewSwitcher() {
        super();
        this.init();
    }

    private void init() {
        Button songs = new Button("Songs");
        Button albums = new Button("Albums");
        songs.setId("b1");

        VBox libraryPane = TracksPane.getInstance();
        HBox albumsPane = AlbumsPane.getInstance();

        songs.setOnMouseClicked(e -> {
            MainScene.getMainPane().setCenter(libraryPane);
        });

        albums.setOnMouseClicked(e -> {
            MainScene.getMainPane().setCenter(albumsPane);
        });

        this.setFillHeight(true);
        this.setId("view-switcher");
        this.getChildren().add(songs);
        this.getChildren().add(albums);

        MainScene.getMainPane().setCenter(libraryPane);
    }

    public static ViewSwitcher getInstance() {
        if (ViewSwitcher.instance == null) {
            ViewSwitcher.instance = new ViewSwitcher();
        }

        return ViewSwitcher.instance;
    }
}
