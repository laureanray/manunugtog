package tech.laureanray.ui.panes.library;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class TracksPane extends VBox {
    private static TracksPane instance;

    private TracksPane() {
        super();
        this.initialize();
        this.setId("tracks-pane");
    }

    private void initialize() {
        Text text = new Text("LibraryPane");
        ScrollPane sp = new ScrollPane();
        VBox vb = new VBox();
        this.getChildren().add(text);
        this.getChildren().add(sp);
        vb.setPrefHeight(500); // FIXME: set fixed height
        VBox.setVgrow(sp, Priority.ALWAYS);

        List<TrackListItem> listItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TrackListItem item = new TrackListItem(String.valueOf(i),"Title", "Artist", "3:12");
            listItems.add(item);
            vb.getChildren().add(item);
        }


        sp.setContent(vb);
    }

    public static TracksPane getInstance() {
        if (instance == null) {
            instance = new TracksPane();
        }

        return instance;
    }
}
