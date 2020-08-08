package tech.laureanray.app;

public class ApplicationDataManager {
    private static ApplicationDataManager instance;

    static {
    }

    private ApplicationDataManager() {
        /*
            TODO:
                1. Do all the initializations here
         */
    }

    public static ApplicationDataManager getInstance() {
        if (instance == null) {
            instance = new ApplicationDataManager();

            /*
            TODO:
                1. Create app data file if not exits
                2. Load app data file
             */


        }
        return instance;
    }

    public static void loadData() {

    }

    public static void updateData() {

    }

}
