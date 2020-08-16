package tech.laureanray.controller;

import javafx.application.Platform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.models.Configuration;
import tech.laureanray.threads.LoadMusicThread;
import tech.laureanray.ui.scenes.MainScene;
import tech.laureanray.ui.elements.library.TracksPane;

import java.io.File;

public class MenuController {
    private static MenuController instance;
    private static Stage stage;
    private ApplicationConfigManager configManager;
    private Configuration config;
    private MenuController() {
        this.configManager = ApplicationConfigManager.getInstance();
        this.config = this.configManager.getConfiguration();

    }

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
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

    public void exit() {
        Platform.exit();
    }

    public void refreshLibrary() {
        System.out.println("refreshLibrary()");
        TracksPane.getInstance().updateTrackList();
    }
}
