/**
 * LoadMusicThread
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/16/20
 */

package tech.laureanray.threads;

import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.models.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoadMusicThread  extends Thread {
    private Configuration config;
    public LoadMusicThread() {
        System.out.println("LoadMusicThread construct");
        this.config = ApplicationConfigManager.getInstance().getConfiguration();
    }

    @Override
    public void run() {
        System.out.println("Thread running");
        List<String> directories = this.config.getTargetDirectories();

        for (String directory: directories) {
            // Traverse
            try {
                Files.walk(Paths.get(directory))
                        .filter(Files::isRegularFile)
                        .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
