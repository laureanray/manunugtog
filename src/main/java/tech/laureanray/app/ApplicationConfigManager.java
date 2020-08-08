/**
 * ApplicationConfigManager class will do stuffs related to application configuration
 * and saving application state persistently to local disk.
 *
 * @author Laurean Ray Bahala
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.app;

public class ApplicationConfigManager {
    private static ApplicationConfigManager instance;
    private ApplicationConfigManager() {
          /*
            TODO:
                1. Create configuration file (os specific) if a config doesnt exists.
                    Then populate with default values
         */
    }

    public static ApplicationConfigManager getInstance() {
        if (instance == null) {
            instance = new ApplicationConfigManager();
        }

        return instance;
    }

    public static void loadConfiguration() { };
    public static void updateConfiguration() { };
}
