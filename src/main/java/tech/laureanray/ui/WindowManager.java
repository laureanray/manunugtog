/**
 * WindowManager
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/16/20
 */

package tech.laureanray.ui;

import javafx.stage.Stage;
import tech.laureanray.ui.strings.UI;

public class WindowManager {
    private static WindowManager instance;
    private Stage stage;
    private WindowManager() {
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }


    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();
        }

        return instance;
    }


    public void showSettings() {
        SettingsScene settingsScene = SettingsScene.getInstance();
        Stage settingsStage = new Stage();
        settingsStage.setTitle(UI.SETTINGS);
        settingsStage.setScene(settingsScene);
        settingsStage.showAndWait();
    }

        }

