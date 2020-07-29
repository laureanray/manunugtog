package tech.laureanray;

import javafx.application.Application;
import javafx.stage.Stage;
import tech.laureanray.scenes.MainScene;

import java.io.IOException;

public class Main extends Application {

    private final MainScene mainScene = new MainScene();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Manunugtog");
        stage.setScene(mainScene.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}