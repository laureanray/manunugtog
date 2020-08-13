package tech.laureanray.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tech.laureanray.ui.panes.library.AlbumsPane;
import tech.laureanray.ui.panes.library.TracksPane;

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
