package tech.laureanray.panes.library;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Library extends VBox {
    private static Library instance;

    private Library() {
        super();
        this.initialize();
    }

    private void initialize() {
        Text text = new Text("LibraryPane");
        ScrollPane sp = new ScrollPane();
        VBox vb = new VBox();

        this.getChildren().add(text);
        this.getChildren().add(sp);
        VBox.setVgrow(sp, Priority.ALWAYS);

        List<TrackListItem> listItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            TrackListItem item = new TrackListItem(String.valueOf(i),"Title", "Artist", "3:12");
            listItems.add(item);
            vb.getChildren().add(item);
        }


        sp.setContent(vb);
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }

        return instance;
    }
}
