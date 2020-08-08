package tech.laureanray.ui;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import tech.laureanray.controller.FileController;
import tech.laureanray.ui.strings.UIStrings;

public class MenuToolbar extends MenuBar {
    private static MenuToolbar instance;
    private static final FileController fileController = FileController.getInstance();
    private MenuToolbar() {
        super();
        this.init();
    }

    private void init() {
        Menu fileMenu  = new Menu(UIStrings.FILE);
        Menu helpMenu = new Menu(UIStrings.HELP);
        Menu viewMenu = new Menu(UIStrings.VIEW);
        // Submenus
        Menu windowsSubMenu = new Menu(UIStrings.WINDOWS);

        MenuItem addFolder = new MenuItem(UIStrings.ADD_FOLDER);
        MenuItem exit = new MenuItem(UIStrings.EXIT);
        MenuItem about = new MenuItem(UIStrings.ABOUT);

        // View
        CheckMenuItem nowPlaying = new CheckMenuItem(UIStrings.NOW_PLAYING);

        windowsSubMenu.getItems().addAll(nowPlaying);

        fileMenu.getItems().add(addFolder);
        fileMenu.getItems().add(exit);
        helpMenu.getItems().add(about);
        viewMenu.getItems().add(windowsSubMenu);


        this.getMenus().add(fileMenu);
        this.getMenus().add(helpMenu);
        this.getMenus().add(viewMenu);
    }

    private void setListeners() {
        this.getMenus().forEach(menu -> {
            if (menu.getText().equals(UIStrings.FILE)) {
                menu.getItems().forEach(menuItem -> {
                    switch (menuItem.getText()) {
                        case UIStrings.ADD_FOLDER:
                            menuItem.setOnAction(e -> {
                                fileController.addFolder();
                            });
                            break;
                    }
                });
            } else if (menu.getText().equals(UIStrings.VIEW)) {
                menu.getItems().forEach(menuItem -> {
                    System.out.println(menuItem.getText());
                    ((Menu) menuItem).getItems().forEach(m -> {
                        switch (m.getText()) {
                            case UIStrings.NOW_PLAYING:
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
