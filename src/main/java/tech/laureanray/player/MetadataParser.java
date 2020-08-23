/**
 * MetadataParser
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/23/20
 */

package tech.laureanray.player;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import tech.laureanray.models.Track;
import tech.laureanray.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetadataParser {
    static {
        var pin = new Logger[]{ Logger.getLogger("org.jaudiotagger") };

        for (Logger l : pin)
            l.setLevel(Level.OFF);
    }
    public static Track parse(String pathname) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        var track = new Track();
        var fileToParse = new File(pathname);
        var audioFile = AudioFileIO.read(fileToParse);
        var tag = audioFile.getTag();

        // FIXME: Fix this later, add a check if a file is really it's type.
        var extension = FileUtils.getFileExt(pathname);

        if (extension.equals("flac")) {
            tag.getFields().forEachRemaining(e -> {
                System.out.println(e.getId() + ":" + e.toString());
                switch (e.getId()) {
                    case "TITLE":
                        track.setTitle(e.toString());
                        break;
                    case "ARTIST":
                        track.getArtists().add(e.toString()); // FIXME: Handle multiple artists
                        break;
                    case "ALBUM":
                        track.setAlbum(e.toString());
                        break;
                    case "LENGTH":
                        track.setDurationInSeconds(Integer.parseInt(e.toString()));
                        break;
                }
            });
            // Get the album art here.
            track.setAlbumArt(tag.getFirstArtwork().getBinaryData());
        } else if (extension.equals("mp3")) {

        }

        return track;
    }
}
