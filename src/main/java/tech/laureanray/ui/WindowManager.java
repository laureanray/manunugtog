/**
 * WindowManager
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/16/20
 */

package tech.laureanray.ui;

import javafx.stage.Modality;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationState;
import tech.laureanray.ui.scenes.SettingsScene;
import tech.laureanray.ui.strings.UI;

public class WindowManager {
    private static WindowManager instance;
    private Stage settingsStage;
    private ApplicationState state = ApplicationState.getState();
    private WindowManager() {
        this.settingsStage = new Stage();
        state.setSettingsStage(this.settingsStage);
    }



    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();

        }

        return instance;
    }


    public void showSettings() {
        SettingsScene settingsScene = SettingsScene.getInstance();
        settingsStage.setTitle(UI.PREFERENCES);
        settingsStage.setScene(settingsScene);
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.showAndWait();
    }

}

