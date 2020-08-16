package tech.laureanray.controller;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.models.Configuration;
import tech.laureanray.threads.LoadMusicThread;
import tech.laureanray.ui.MainScene;

import java.io.File;

public class FileController {
    private static FileController instance;
    private static Stage stage;
    private ApplicationConfigManager configManager;
    private Configuration config;
    private FileController() {
        this.configManager = ApplicationConfigManager.getInstance();
        this.config = this.configManager.getConfiguration();

    }

    public static FileController getInstance() {
        if (instance == null) {
            instance = new FileController();
            stage = MainScene.getStage();
        }

        return instance;
    }

    public void addFolder() {
        // TODO: prevent duplicate folders, prevent nested folders with already included parent from adding
        DirectoryChooser chooser = new DirectoryChooser();
        File path = chooser.showDialog(stage);

        if (!this.config.getTargetDirectories().contains(path.toString())) {
            // FIXME: prevent nested
            this.config.getTargetDirectories().add(path.toString());
            this.loadMusic();
        }

        configManager.updateConfiguration();
    }

    public void loadMusic()  {
        LoadMusicThread loadMusicThread = new LoadMusicThread();
        loadMusicThread.run();
    }

}
