/**
 * Playah
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.player;

import org.jflac.PCMProcessor;
import org.jflac.metadata.StreamInfo;
import org.jflac.util.ByteData;

import javax.sound.sampled.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class Player implements PCMProcessor {
    private AudioFormat fmt;
    private DataLine.Info info;
    private SourceDataLine line;
    private Vector listeners = new Vector();

    public void processStreamInfo(StreamInfo streamInfo) {
        try {
            this.fmt = streamInfo.getAudioFormat();
            this.info = new DataLine.Info(SourceDataLine.class, this.fmt, -1);
            this.line = (SourceDataLine) AudioSystem.getLine(this.info);
            int size = this.listeners.size();

            for(int index = 0; index < size; ++index) {
                this.line.addLineListener((LineListener)this.listeners.get(index));
            }

            this.line.open(this.fmt, -1);
            this.line.start();
        } catch (LineUnavailableException var4) {
            var4.printStackTrace();
        }
    }

    public void processPCM(ByteData pcm) {
        this.line.write(pcm.getData(), 0, pcm.getLen());
    }

    public void decode(String inFileName) throws IOException, LineUnavailableException {
        FileInputStream is = new FileInputStream(inFileName);
        ExtendedDecoder decoder = new ExtendedDecoder(is);
        decoder.addPCMProcessor(this);

        try {
            decoder.play();
        } catch (EOFException var5) {
        }

        this.line.drain();
        this.line.close();
        this.listeners.clear();
    }
}
