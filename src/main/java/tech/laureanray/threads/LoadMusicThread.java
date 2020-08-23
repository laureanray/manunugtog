/**
 * LoadMusicThread
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/16/20
 */

package tech.laureanray.threads;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import tech.laureanray.app.ApplicationConfigManager;
import tech.laureanray.app.ApplicationDataManager;
import tech.laureanray.models.ApplicationData;
import tech.laureanray.models.Configuration;
import tech.laureanray.player.MetadataParser;
import tech.laureanray.ui.elements.library.TracksPane;
import tech.laureanray.utils.FileUtils;

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
                            var ext = FileUtils.getFileExt(e.toString());
                            if (ext.equals("flac") || ext.equals("mp3")) { // FIXME: add the checks later for other format
                                try {
                                    var t = MetadataParser.parse(e.toString());
                                    this.applicationDataManager.addTrack(t);
                                } catch (TagException tagException) {
                                    tagException.printStackTrace();
                                } catch (ReadOnlyFileException readOnlyFileException) {
                                    readOnlyFileException.printStackTrace();
                                } catch (CannotReadException cannotReadException) {
                                    cannotReadException.printStackTrace();
                                } catch (InvalidAudioFrameException invalidAudioFrameException) {
                                    invalidAudioFrameException.printStackTrace();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        });
                TracksPane.getInstance().updateTrackList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
