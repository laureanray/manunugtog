/**
 * AlbumsPane
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.ui.elements.library;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AlbumsPane extends HBox {
    private static AlbumsPane instance;

    private AlbumsPane() {
        super();
        this.setId("albums-pane");
        this.init();
    }

    private void init() {
        Text text = new Text("Albums");
        this.getChildren().add(text);
    }

    public static AlbumsPane getInstance() {
        if (instance == null) {
            instance = new AlbumsPane();
        }

        return instance;
    }
}
