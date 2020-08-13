/**
 * ApplicationConfigManager class will do stuffs related to application configuration
 * and saving application state persistently to local disk.
 *
 * @author Laurean Ray Bahala
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.app;

import com.alibaba.fastjson.JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationConfigManager {
    private static ApplicationConfigManager instance;
    private ApplicationConfigManager() {
          /*
            TODO:
                1. Create configuration file (os specific) if a config doesnt exists.
                    Then populate with default values
         */


        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("HAHAHAHAHA" + String.valueOf(i) + "\n");
        }

        String jsonString = JSON.toJSONString(data);

        try {
            FileWriter myWriter = new FileWriter("application-config.json");
            myWriter.write(jsonString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static ApplicationConfigManager getInstance() {
        if (instance == null) {
            instance = new ApplicationConfigManager();
        }

        return instance;
    }

    public void loadConfiguration() {
        /*
            TODO:
                1. Load the configuration file (.json)
                2. Then load it to the memory (sync with ApplicationState)

         */
    };
    public void updateConfiguration() {

    };
}
