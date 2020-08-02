package tech.laureanray.app;

public class AppDataManager {
    private static AppDataManager fileManager;

    private AppDataManager() {
        /*
            TODO:
                1. Do all the initializations here
         */
    }

    public static AppDataManager getFileManager() {
        if (fileManager == null) {
            fileManager = new AppDataManager();

            /*
            TODO:
                1. Create app data file if not exits
                2. Load app data file
             */

        }
        return fileManager;
    }

}
