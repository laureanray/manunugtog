package tech.laureanray;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.app.ApplicationDataManager;
import tech.laureanray.app.ApplicationProperties;
import tech.laureanray.ui.MainScene;
import tech.laureanray.ui.SettingsScene;
import tech.laureanray.utils.Keybindings;

import java.io.IOException;

public class Main extends Application {

    private final MainScene mainScene = MainScene.getInstance();
    private final SettingsScene settingsScene = SettingsScene.getInstance();
    private final ApplicationDataManager appDataManager = ApplicationDataManager.getInstance();
    private final ApplicationConfigManager configManager = ApplicationConfigManager.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(ApplicationProperties.NAME);
        stage.setScene(mainScene);
        stage.show();
        mainScene.setStage(stage);
        Keybindings.initialize(mainScene);
//
//        Stage settingsStage = new Stage();
//        settingsStage.setTitle("Settings");
//        settingsStage.setScene(settingsScene);
//        settingsStage.show();

        stage.setOnCloseRequest(windowEvent -> {
            configManager.updateConfiguration();
        });
        Platform.runLater( () -> stage.requestFocus() );
    }

    public static void main(String[] args) {
        launch();
    }

}