/**
 * TrackListItem
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.ui.panes.library;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TrackListItem extends HBox {
    TrackListItem(String id, String title, String artist, String trackLength) {
        super();
        Text titleText = new Text(title);
        titleText.prefWidth(250);
        Text artistText = new Text(artist);
        Text trackLengthText = new Text(trackLength);
        this.getChildren().addAll(titleText, artistText, trackLengthText);
        this.setId(id);
        this.setOnMouseClicked(evt -> {
            System.out.println("Clicked: " + this.getId());
        });
        this.getStyleClass().add("tracklist-item");
    }
}