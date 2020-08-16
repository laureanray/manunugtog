package tech.laureanray.ui;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import tech.laureanray.controller.FileController;
import tech.laureanray.ui.strings.UI;

public class MenuToolbar extends MenuBar {
    private static MenuToolbar instance;
    private static final FileController fileController = FileController.getInstance();
    private MenuToolbar() {
        super();
        this.init();
        this.setListeners();
    }

    private void init() {
        Menu fileMenu  = new Menu(UI.FILE);
        Menu helpMenu = new Menu(UI.HELP);
        Menu viewMenu = new Menu(UI.VIEW);
        // Submenus
        Menu windowsSubMenu = new Menu(UI.WINDOWS);

        MenuItem addFolder = new MenuItem(UI.ADD_FOLDER);
        MenuItem exit = new MenuItem(UI.EXIT);
        MenuItem about = new MenuItem(UI.ABOUT);

        // View
        CheckMenuItem nowPlaying = new CheckMenuItem(UI.NOW_PLAYING);

        windowsSubMenu.getItems().addAll(nowPlaying);

        fileMenu.getItems().add(addFolder);
        fileMenu.getItems().add(exit);
        helpMenu.getItems().add(about);
        viewMenu.getItems().add(windowsSubMenu);


        this.getMenus().add(fileMenu);
        this.getMenus().add(helpMenu);
        this.getMenus().add(viewMenu);
        this.managedProperty().bind(this.visibleProperty());
    }

    private void setListeners() {
        this.getMenus().forEach(menu -> {
            if (menu.getText().equals(UI.FILE)) {
                menu.getItems().forEach(menuItem -> {
                    switch (menuItem.getText()) {
                        case UI.ADD_FOLDER:
                            menuItem.setOnAction(e -> {
                                System.out.println("ADD FOLDER");
                                fileController.addFolder();
                            });
                            break;
                    }
                });
            } else if (menu.getText().equals(UI.VIEW)) {
                menu.getItems().forEach(menuItem -> {
                    System.out.println(menuItem.getText());
                    ((Menu) menuItem).getItems().forEach(m -> {
                        switch (m.getText()) {
                            case UI.NOW_PLAYING:
                                m.setOnAction(e -> {
                                    System.out.println("Now Playing");
                                });
                                break;
                        }
                    });
                });
            }
        });
    }



    public static MenuToolbar getInstance() {
        if (MenuToolbar.instance == null) {
            MenuToolbar.instance = new MenuToolbar();
        }

        return instance;
    }
}
