/**
 * SettingsScene
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.ui;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import static tech.laureanray.app.ApplicationProperties.SETTINGS_HEIGHT;
import static tech.laureanray.app.ApplicationProperties.SETTINGS_WIDTH;

public class SettingsScene extends Scene {
    private static SettingsScene instance;
    private static HBox parent = new HBox();
    private SettingsScene() {
        super(parent, SETTINGS_WIDTH, SETTINGS_HEIGHT);
        this.init();
    }

    private void init() {
        Text text = new Text("Settings");
        parent.getChildren().add(text);
    }

    public static SettingsScene getInstance() {
        if (instance == null) {
            instance = new SettingsScene();
        }

        return instance;
    }


}
