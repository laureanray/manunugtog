/**
 * TestPlayer
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.player;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

//class SayHello extends TimerTask {
//    public void run() {
//        TestPlayer.player.
//    }
//}

public class TestPlayer {
    public static org.jflac.apps.Player player = new org.jflac.apps.Player();
    public static void main(String[] args) throws IOException, LineUnavailableException {
        Player player = new Player();
        player.decode("music.flac");
    }
}
