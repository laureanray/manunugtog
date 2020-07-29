package tech.laureanray.app;

public class AppDataManager {
    private AppDataManager fileManager;

    private AppDataManager() {

    }

    public AppDataManager getFileManager() {
        if (fileManager == null) {
            this.fileManager = new AppDataManager();

            /*
            TODO:
                1. Create app data file if not exits
                2. Load app data file
             */

        }
        return fileManager;
    }

}
