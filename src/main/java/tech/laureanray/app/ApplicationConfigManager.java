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
import tech.laureanray.ui.strings.FILE;

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

        File configFile = new File(FILE.APP_CONFIG);
        if (configFile.exists()) {
            System.out.println("FILE EXISTS");
            System.out.println(configFile.toString());
            if (configFile.canRead()) {
                try {
                    String content = Files.readString(configFile.getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
                    loadedConfiguration = JSON.parseObject(content, Configuration.class);
                } catch (IOException e) {
                    // handle error here
                }
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter(FILE.APP_CONFIG);
                myWriter.write(jsonString);
                myWriter.close();
                this.loadedConfiguration = defaultConfiguration;
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

    // This loads the configuration and sets the application state
    public void loadConfiguration() {
        System.out.println("loadConfiguration()");
        if (this.loadedConfiguration != null) {
            ApplicationState.getState().setWindowPosition(this.loadedConfiguration.getPositionX(), this.loadedConfiguration.getPositionY());
        }
    }

    public void updateConfiguration() {
        try {
            FileWriter myWriter = new FileWriter(FILE.APP_CONFIG);
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
