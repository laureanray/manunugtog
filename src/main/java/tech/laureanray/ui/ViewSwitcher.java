package tech.laureanray.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tech.laureanray.panes.library.Library;

public class ViewSwitcher extends HBox  {
    private static ViewSwitcher instance;

    private ViewSwitcher() {
        super();
        init();
    }

    private void init() {
        Button songs = new Button("Songs");
        Button albums = new Button("Albums");
        songs.setId("b1");

        HBox libraryPane = Library.getInstance();
        HBox albumsView = new HBox(new Text("Albums"));

        songs.setOnMouseClicked(e -> {
            MainScene.getMainPane().setCenter(libraryPane);
        });

        albums.setOnMouseClicked(e -> {
            MainScene.getMainPane().setCenter(albumsView);
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
