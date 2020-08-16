package tech.laureanray.app;

import javafx.stage.Stage;

public class ApplicationState {
    private static ApplicationState state;

    private View currentView;
    private int currentWindowX;
    private int getCurrentWindowX;
    private Stage mainStage;
    private Stage settingsStage;

    private ApplicationState() {
    }

    public static ApplicationState getState() {
        if (state == null) {
            state = new ApplicationState();
        }
        return state;
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    public void setSettingsStage(Stage stage) {
        this.settingsStage = stage;
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }

    public int getCurrentWindowX() {
        return currentWindowX;
    }

    public void setCurrentWindowX(int currentWindowX) {
        this.currentWindowX = currentWindowX;
    }

    public int getGetCurrentWindowX() {
        return getCurrentWindowX;
    }

    public void setGetCurrentWindowX(int getCurrentWindowX) {
        this.getCurrentWindowX = getCurrentWindowX;
    }

    public void setWindowPosition(int x, int y) {
        if (this.mainStage != null) {
            this.mainStage.setX(x);
            this.mainStage.setY(y);
        }
    }
}
