/**
 * Metadata
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/20/20
 */

package tech.laureanray.experimental;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v23Frame;

import java.io.File;
import java.io.IOException;

public class Metadata {
    public static void main(String[] args) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        var m = new File("/home/lr/Music/Taylor Swift/Taylor Swift - folklore (deluxe version)/1.mp3");
        AudioFile f = AudioFileIO.read(m);
        Tag tag = f.getTag();
        System.out.println(f.getAudioHeader().getBitRate());
        tag.getFields().forEachRemaining(e -> {
            System.out.println(e.getId() + ": " + ((ID3v23Frame) e).getContent());
        });
    }
}
