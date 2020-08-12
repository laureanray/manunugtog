/**
 * CustomPCMProcessors 
 *
 * @author  Laurean Ray
 * @version 1.0
 * @since   8/13/20
 */

package tech.laureanray.player;

import org.jflac.PCMProcessor;
import org.jflac.metadata.StreamInfo;
import org.jflac.util.ByteData;

import java.util.HashSet;
import java.util.Iterator;

public class CustomPCMProcessors implements PCMProcessor {
    private HashSet pcmProcessors = new HashSet();

    CustomPCMProcessors() {
    }

    public void addPCMProcessor(PCMProcessor processor) {
        synchronized(this.pcmProcessors) {
            this.pcmProcessors.add(processor);
        }
    }

    public void removePCMProcessor(PCMProcessor processor) {
        synchronized(this.pcmProcessors) {
            this.pcmProcessors.remove(processor);
        }
    }

    public void processStreamInfo(StreamInfo info) {
        synchronized(this.pcmProcessors) {
            Iterator it = this.pcmProcessors.iterator();

            while(it.hasNext()) {
                PCMProcessor processor = (PCMProcessor)it.next();
                processor.processStreamInfo(info);
            }

        }
    }

    public void processPCM(ByteData pcm) {
        synchronized(this.pcmProcessors) {
            Iterator it = this.pcmProcessors.iterator();

            while(it.hasNext()) {
                PCMProcessor processor = (PCMProcessor)it.next();
                processor.processPCM(pcm);
            }

        }
    }
}
