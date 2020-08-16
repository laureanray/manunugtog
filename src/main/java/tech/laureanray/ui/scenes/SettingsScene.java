/**
 * SettingsScene
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
        var tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        var librarySettings = new Tab("Library");

        var librarySettingsContent = new VBox();
            librarySettingsContent.getStyleClass().add("preferences-content");

        var libraryHeaderText = new Text("Music Directory");
            libraryHeaderText.getStyleClass().add("preferences-header");

        var libraryListview = new ListView<String>();
            libraryListview.getItems().add("Test item");


            librarySettingsContent.getChildren().addAll(
                    libraryHeaderText,
                    libraryListview
            );

            librarySettings.setContent(librarySettingsContent);



        var playerSettings = new Tab("Player");
        var lookSettings = new Tab("Look & Feel");


        tabPane.getTabs().addAll(
                librarySettings,
                playerSettings,
                lookSettings
        );


        parent.getChildren().addAll(tabPane);
    }

    public static SettingsScene getInstance() {
        if (instance == null) {
            instance = new SettingsScene();
            instance.getStylesheets().add("default.css");
        }

        return instance;
    }


}
