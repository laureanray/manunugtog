package tech.laureanray;

import javafx.application.Application;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.app.ApplicationDataManager;
import tech.laureanray.app.ApplicationProperties;
import tech.laureanray.app.ApplicationState;
import tech.laureanray.ui.MainScene;
import tech.laureanray.ui.SettingsScene;
import tech.laureanray.utils.Keybindings;

import java.io.IOException;

public class Main extends Application {

    private final MainScene mainScene = MainScene.getInstance();
    private final SettingsScene settingsScene = SettingsScene.getInstance();
    private final ApplicationDataManager appDataManager = ApplicationDataManager.getInstance();
    private final ApplicationState appState = ApplicationState.getState();
    private final ApplicationConfigManager configManager = ApplicationConfigManager.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        /*
            TODO:
             1. This tart method is doing a lot of things, delegate some of the things to another class or method
             2. Also, refactor the stage initialization move it probably to WindowManager
         */
        stage.setTitle(ApplicationProperties.NAME);
        stage.setScene(mainScene);


        mainScene.setStage(stage);


        appState.setMainStage(stage);

        stage.xProperty().addListener((obs, oldVal, newVal) -> {
            configManager.getConfiguration().setPositionX(newVal.intValue());
            configManager.updateConfiguration();
        });
        stage.yProperty().addListener((obs, oldVal, newVal) -> {
            configManager.getConfiguration().setPositionY(newVal.intValue());
            configManager.updateConfiguration();
        });


        Keybindings.initialize(mainScene);
        // Load application configuration
        configManager.loadConfiguration();

        stage.setOnCloseRequest(windowEvent -> {
            configManager.updateConfiguration();
        });

        // Finally show stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}