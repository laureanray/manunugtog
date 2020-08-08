package tech.laureanray;

import javafx.application.Application;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.app.ApplicationProperties;
import tech.laureanray.app.ApplicationDataManager;
import tech.laureanray.ui.MainScene;

import java.io.IOException;

public class Main extends Application {

    private final MainScene mainScene = MainScene.getInstance();
    private final ApplicationDataManager appDataManager = ApplicationDataManager.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(ApplicationProperties.NAME);
        stage.setScene(mainScene);
        stage.show();
        mainScene.setStage(stage);

        stage.setOnCloseRequest(windowEvent -> {
            ApplicationConfigManager.updateConfiguration();
        });
    }

    public static void main(String[] args) {
        launch();
    }

}