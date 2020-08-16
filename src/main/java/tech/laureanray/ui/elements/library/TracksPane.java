package tech.laureanray.ui.elements.library;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tech.laureanray.app.ApplicationDataManager;
import tech.laureanray.models.Track;

import java.util.ArrayList;
import java.util.List;


public class TracksPane extends VBox {
    private static TracksPane instance;
    private ScrollPane scrollPane = new ScrollPane();
    private VBox vb = new VBox();
    private TracksPane() {
        super();
        this.initialize();
        this.setId("tracks-pane");
    }

    private void initialize() {
        Text text = new Text("LibraryPane");
        this.getChildren().add(text);
        this.getChildren().add(scrollPane);

        this.updateTrackList();
    }

    public void updateTrackList() {
        System.out.println((char)27 + "[31m" + "updateTrackList()");
//        vb.setPrefHeight(500); // FIXME: set fixed height
        vb.getChildren().removeAll(); // Remove all
        List<TrackListItem> listItems = new ArrayList<>();
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        List<Track> tracks = ApplicationDataManager.getInstance().getTracks();

        for (Track track: tracks) {
            TrackListItem item = new TrackListItem(track.hashCode(), track.getTitle(), "Artist", "3:12");
            listItems.add(item);
            vb.getChildren().add(item);
        }

        scrollPane.setContent(vb);
    }

    public static TracksPane getInstance() {
        if (instance == null) {
            instance = new TracksPane();
        }

        return instance;
    }
}
