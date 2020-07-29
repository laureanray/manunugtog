package tech.laureanray.app;

public class AppState {
    private AppState state;

    private View currentView;
    private int currentWindowX;
    private int getCurrentWindowX;

    private AppState () {
        /*
        TODO:
            1. Check if library is loaded here.

         */
    }

    public AppState getState() {
        if (state == null) {
            this.state = new AppState();
        }
        return state;
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
}
