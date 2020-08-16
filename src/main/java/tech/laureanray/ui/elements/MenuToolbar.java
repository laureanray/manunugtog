package tech.laureanray.ui.elements;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import tech.laureanray.controller.MenuController;
import tech.laureanray.ui.strings.UI;

public class MenuToolbar extends MenuBar {
    private static MenuToolbar instance;
    private static final MenuController menuController = MenuController.getInstance();
    private MenuToolbar() {
        super();
        this.init();
    }

    private void init() {
        var fileMenu  = new Menu(UI.FILE);
        var helpMenu = new Menu(UI.HELP);
        var viewMenu = new Menu(UI.VIEW);
        var libraryMenu = new Menu(UI.LIBRARY);

        var windowsSubMenu = new Menu(UI.WINDOWS);
        var addFolder = new MenuItem(UI.ADD_FOLDER);
            addFolder.setOnAction(e -> menuController.addFolder());
        var exit = new MenuItem(UI.EXIT);
            exit.setOnAction(e -> menuController.exit());
        var settings = new MenuItem(UI.PREFERENCES);
            settings.setOnAction(e -> menuController.showSettings());
        var about = new MenuItem(UI.ABOUT);
        var refresh = new MenuItem(UI.REFRESH_LIBRARY);
            refresh.setOnAction(e -> menuController.refreshLibrary());
        // View
        CheckMenuItem nowPlaying = new CheckMenuItem(UI.NOW_PLAYING);

        windowsSubMenu.getItems().addAll(nowPlaying);

        fileMenu.getItems().addAll(addFolder, settings, exit);
        libraryMenu.getItems().add(refresh);
        helpMenu.getItems().add(about);
        viewMenu.getItems().add(windowsSubMenu);

        this.getMenus().addAll(fileMenu, libraryMenu, helpMenu, viewMenu);
        this.managedProperty().bind(this.visibleProperty());
    }

    public static MenuToolbar getInstance() {
        if (MenuToolbar.instance == null) {
            MenuToolbar.instance = new MenuToolbar();
        }

        return instance;
    }
}
