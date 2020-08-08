package tech.laureanray.app;

public class ApplicationDataManager {
    private static ApplicationDataManager fileManager;

    private ApplicationDataManager() {
        /*
            TODO:
                1. Do all the initializations here
         */
    }

    public static ApplicationDataManager getFileManager() {
        if (fileManager == null) {
            fileManager = new ApplicationDataManager();

            /*
            TODO:
                1. Create app data file if not exits
                2. Load app data file
             */

            System.getProperties().list(System.out);

        }
        return fileManager;
    }

}
