/**
 * TrackDetailsPane
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.ui.elements;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TrackDetailsPane extends HBox {
    private static TrackDetailsPane instance;

    private TrackDetailsPane() {
        super();
        this.init();
        this.setId("trackdetails");
    }

    private void init() {
        Text format = new Text("FLAC");
        Text bitrate = new Text("951 kpps");
        Text bitDepth = new Text("16 bit");
        Text sampleRate = new Text("44.1 kHz");

        this.getChildren().addAll(format, bitrate, bitDepth, sampleRate);
    }

    public static TrackDetailsPane getInstance() {
        if (instance == null) {
            instance = new TrackDetailsPane();

        }
        return instance;
    }
}
