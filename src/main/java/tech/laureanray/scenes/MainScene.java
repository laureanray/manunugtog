package tech.laureanray.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MainScene {
    private final Scene scene;
    private final MenuBar menuBar = new MenuBar();
    private final VBox vbox;

    public MainScene() {
        MenuBar bar = createMenuBar();
        this.vbox = new VBox(bar);
        scene = new Scene(this.vbox, 400, 600);
        scene.getStylesheets().add("menu.css");
    }

    public Scene getScene() {
        return scene;
    }


    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
//      FILE
        Menu fileMenu  = new Menu("File");

        MenuItem addFolder = new MenuItem("Add Folder");
        MenuItem exit = new MenuItem("Exit");

        fileMenu.getItems().add(addFolder);
        fileMenu.getItems().add(exit);

//      HELP
        Menu helpMenu = new Menu("Help");

        MenuItem about = new MenuItem("About");


        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(helpMenu);
        return menuBar;
    }

    public VBox getVbox() {
        return vbox;
    }
}
