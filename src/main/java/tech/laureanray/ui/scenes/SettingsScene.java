/**
 * SettingsScene
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static tech.laureanray.app.ApplicationProperties.SETTINGS_HEIGHT;
import static tech.laureanray.app.ApplicationProperties.SETTINGS_WIDTH;

public class SettingsScene extends Scene {
    private static SettingsScene instance;
    private static VBox parent = new VBox();
    private SettingsScene() {
        super(parent, SETTINGS_WIDTH, SETTINGS_HEIGHT);
        this.init();
    }

    private void init() {
        Text text = new Text("Settings");
        TabPane tabPane = new TabPane();
        Tab generalSettings = new Tab("General");
        tabPane.getTabs().add(generalSettings);


        parent.getChildren().addAll(text, tabPane);
    }

    public static SettingsScene getInstance() {
        if (instance == null) {
            instance = new SettingsScene();
        }

        return instance;
    }


}
