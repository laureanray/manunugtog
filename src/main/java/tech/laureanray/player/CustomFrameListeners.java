/**
 * FrameListeners
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.player;

import org.jflac.FrameListener;
import org.jflac.frame.Frame;
import org.jflac.metadata.Metadata;

import java.util.HashSet;
import java.util.Iterator;

public class CustomFrameListeners implements FrameListener {
    private HashSet frameListeners = new HashSet();

    CustomFrameListeners() {
    }

    public void addFrameListener(FrameListener listener) {
        synchronized(this.frameListeners) {
            this.frameListeners.add(listener);
        }
    }

    public void removeFrameListener(FrameListener listener) {
        synchronized(this.frameListeners) {
            this.frameListeners.remove(listener);
        }
    }

    public void processMetadata(Metadata metadata) {
        synchronized(this.frameListeners) {
            Iterator it = this.frameListeners.iterator();

            while(it.hasNext()) {
                FrameListener listener = (FrameListener)it.next();
                listener.processMetadata(metadata);
            }

        }
    }

    public void processFrame(Frame frame) {
        synchronized(this.frameListeners) {
            System.out.println("PF");
            Iterator it = this.frameListeners.iterator();

            while(it.hasNext()) {
                FrameListener listener = (FrameListener)it.next();
                listener.processFrame(frame);
            }

        }
    }

    public void processError(String msg) {
        synchronized(this.frameListeners) {
            Iterator it = this.frameListeners.iterator();

            while(it.hasNext()) {
                FrameListener listener = (FrameListener)it.next();
                listener.processError(msg);
            }

        }
    }
}
