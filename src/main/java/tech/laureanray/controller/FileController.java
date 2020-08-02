package tech.laureanray.controller;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import tech.laureanray.ui.MainScene;

import java.io.File;

public class FileController {
    private static FileController instance;
    private static Stage stage;
    private FileController() { }

    public static FileController getInstance() {
        if (instance == null) {
            instance = new FileController();
            stage = MainScene.getStage();
        }

        return instance;
    }

    public void addFolder() {
        DirectoryChooser chooser = new DirectoryChooser();
        File path = chooser.showDialog(stage);
    }
}
