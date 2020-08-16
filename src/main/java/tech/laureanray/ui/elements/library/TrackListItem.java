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
        Text titleText = new Text(title);
        Text artistText = new Text(artist);
        this.setPrefWidth(ApplicationProperties.APP_WIDTH - 30);
        Text trackLengthText = new Text(trackLength);
        this.getChildren().addAll(titleText, artistText, trackLengthText);
        this.setId(String.valueOf(id));
        this.setOnMouseClicked(evt -> {
            System.out.println("Clicked: " + this.getId());
        });
        this.getStyleClass().add("tracklist-item");
    }
}
