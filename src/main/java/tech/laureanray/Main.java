package tech.laureanray;

import javafx.application.Application;
import javafx.stage.Stage;
import tech.laureanray.app.App;
import tech.laureanray.app.AppBootstrapper;
import tech.laureanray.ui.MainScene;

import java.io.IOException;

public class Main extends Application {

    private final MainScene mainScene = MainScene.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        AppBootstrapper.loadConfig();
        stage.setTitle(App.NAME);
        stage.setScene(mainScene);
        stage.show();
        mainScene.setStage(stage);

        stage.setOnCloseRequest(windowEvent -> {
            AppBootstrapper.saveConfig();
        });
    }

    public static void main(String[] args) {
        launch();
    }

}