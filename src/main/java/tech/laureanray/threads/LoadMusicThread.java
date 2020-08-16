/**
 * LoadMusicThread
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/16/20
 */

package tech.laureanray.threads;

import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.app.ApplicationDataManager;
import tech.laureanray.models.ApplicationData;
import tech.laureanray.models.Configuration;
import tech.laureanray.models.Track;
import tech.laureanray.ui.elements.library.TracksPane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoadMusicThread implements Runnable {
    private Configuration config;
    private ApplicationDataManager applicationDataManager;
    private ApplicationData applicationData;
    public LoadMusicThread() {
        System.out.println("LoadMusicThread construct");
        this.config = ApplicationConfigManager.getInstance().getConfiguration();
        this.applicationDataManager = ApplicationDataManager.getInstance();
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
                        .forEach(e -> {
                            var t = new Track(e.toString(), null, null);
                            this.applicationDataManager.addTrack(t);
                        });
                TracksPane.getInstance().updateTrackList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
