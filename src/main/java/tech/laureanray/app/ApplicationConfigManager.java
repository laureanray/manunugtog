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
import tech.laureanray.models.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ApplicationConfigManager {
    private static ApplicationConfigManager instance;
    private Configuration loadedConfiguration;
    private ApplicationConfigManager() {
        Configuration defaultConfiguration = new Configuration();
        String jsonString = JSON.toJSONString(defaultConfiguration);

        File configFile = new File("application-config.json");
        if (configFile.exists()) {
            System.out.println("FILE EXISTS");
            System.out.println(configFile.toString());
                if (configFile.canRead()) { try {
                    String content = Files.readString(configFile.getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
                    loadedConfiguration = JSON.parseObject(content, Configuration.class);
                } catch (IOException e) {
                    // handle error here

                }
            }
        } else {
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
        try {
            FileWriter myWriter = new FileWriter("application-config.json");
            myWriter.write(JSON.toJSONString(loadedConfiguration));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public Configuration getConfiguration() {
        return this.loadedConfiguration;
    }
}
