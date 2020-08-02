package tech.laureanray.ui;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuToolbar extends MenuBar {
    private static MenuToolbar instance;

    private MenuToolbar() {
        super();
        this.init();
    }

    private void init() {
        Menu fileMenu  = new Menu("File");
        Menu helpMenu = new Menu("Help");
        Menu viewMenu = new Menu("View");
        // Submenus
        Menu windowsSubMenu = new Menu("Windows");

        MenuItem addFolder = new MenuItem("Add Folder");
        MenuItem exit = new MenuItem("Exit");
        MenuItem about = new MenuItem("About");

        // View
        CheckMenuItem nowPlaying = new CheckMenuItem("Now Playing");


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
            if (menu.getText().equals("File")) {

            } else if (menu.getText().equals("View")) {
                menu.getItems().forEach(menuItem -> {
                    System.out.println(menuItem.getText());
                    ((Menu) menuItem).getItems().forEach(m -> {
                        switch (m.getText()) {
                            case "Now Playing":
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
