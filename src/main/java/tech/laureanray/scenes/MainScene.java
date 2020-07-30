package tech.laureanray.scenes;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainScene {
    private Scene scene;
    private MenuBar menuBar;
    private VBox vbox;
    private HBox button1Hbox;
    private HBox button2Hbox;
    private BorderPane content;

    public MainScene() {
        initMenuBar();
        addMenuListeners();
        initUI();
    }

    public Scene getScene() {
        return scene;
    }

    private void initUI() {
        this.vbox = new VBox(menuBar);
        this.scene = new Scene(this.vbox, 400, 600);
        this.content = new BorderPane();
        HBox viewSwitcher = new HBox();
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        button1.setId("b1");
        button1Hbox = new HBox(new Text("Button 1"));
        button2Hbox = new HBox(new Text("Button 2"));

        button1.setOnMouseClicked(e -> {
            this.content.setCenter(button1Hbox);
        });

        button2.setOnMouseClicked(e -> {
            this.content.setCenter(button2Hbox);
        });

        viewSwitcher.setFillHeight(true);
        viewSwitcher.setId("view-switcher");
        viewSwitcher.getChildren().add(button1);
        viewSwitcher.getChildren().add(button2);


        this.vbox.getChildren().add(viewSwitcher);

        this.content.setCenter(button1Hbox);

        this.vbox.getChildren().add(content);
        this.scene.getStylesheets().add("menu.css");
    }

    private void initMenuBar() {
        this.menuBar = new MenuBar();

//      FILE
        Menu fileMenu  = new Menu("File");

        MenuItem addFolder = new MenuItem("Add Folder");
        MenuItem exit = new MenuItem("Exit");


        fileMenu.getItems().add(addFolder);
        fileMenu.getItems().add(exit);

//      HELP
        Menu helpMenu = new Menu("Help");

        MenuItem about = new MenuItem("About");

        helpMenu.getItems().add(about);

        this.menuBar.getMenus().add(fileMenu);
        this.menuBar.getMenus().add(helpMenu);
    }

    private void addMenuListeners() {
        this.menuBar.getMenus().forEach(menu -> {
            menu.getItems().forEach(menuItem -> {
                switch (menuItem.getText()) {
                    case "Exit":
                        menuItem.setOnAction(actionEvent -> {
                            Platform.exit();
                            System.exit(0);
                        });
                        break;
                }
            });
        });
    }

    public VBox getVbox() {
        return vbox;
    }
}
