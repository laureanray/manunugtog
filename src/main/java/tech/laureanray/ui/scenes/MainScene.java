package tech.laureanray.ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tech.laureanray.app.ApplicationProperties;
import tech.laureanray.ui.elements.ViewSwitcher;
import tech.laureanray.ui.elements.MenuToolbar;
import tech.laureanray.ui.elements.PlayerPane;
import tech.laureanray.ui.elements.TrackDetailsPane;

public class MainScene extends Scene {
    private static MainScene instance;
    private static MenuBar menuBar;
    private static PlayerPane playerPane;
    private static TrackDetailsPane trackDetailsPane;
    private static VBox parent = new VBox();
    private static HBox viewSwitcher;
    private static BorderPane mainPane = new BorderPane();
    private static Stage stage;
    private MainScene() {
        super(MainScene.parent, ApplicationProperties.APP_WIDTH, ApplicationProperties.APP_HEIGHT);
        init();
    }

    private static void init() {
        MainScene.menuBar = MenuToolbar.getInstance();
        MainScene.viewSwitcher = ViewSwitcher.getInstance();
        MainScene.playerPane = PlayerPane.getInstance();
        MainScene.trackDetailsPane = TrackDetailsPane.getInstance();
        MainScene.parent.getChildren().addAll(menuBar, trackDetailsPane, playerPane, viewSwitcher, mainPane);
        MainScene.menuBar.setVisible(false);
    }

    public static MainScene getInstance() {
        if (MainScene.instance == null) {
            MainScene.instance = new MainScene();
            MainScene.instance.getStylesheets().add("default.css");
        }

        return MainScene.instance;
    }

    public static BorderPane getMainPane() {
        return mainPane;
    }

    public static void setStage(Stage stage) {
        MainScene.stage = stage;
    }

    public static Stage getStage() {
        return MainScene.stage;
    }
}
