/**
 * TrackListItem
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.ui.elements.library;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tech.laureanray.app.ApplicationProperties;

public class TrackListItem extends HBox {
    TrackListItem(int id, String title, String artist, String trackLength) {
        super();
        this.setPrefWidth(ApplicationProperties.APP_WIDTH - 30);
        var titleText = new Text(title);
        var separator = new Text("-");
        var artistText = new Text(artist);
        var trackLengthText = new Text(trackLength);
            trackLengthText.setStyle("-fx-padding: 0 0 0 25px");

        this.getChildren().addAll(titleText, separator, artistText, trackLengthText);
        this.setId(String.valueOf(id));
        this.setOnMouseClicked(evt -> {
            System.out.println("Clicked: " + this.getId());
        });
        this.getStyleClass().add("tracklist-item");
    }
}
