/**
 * TestPlayer
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.test;

import org.jflac.apps.Player;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

//class SayHello extends TimerTask {
//    public void run() {
//        TestPlayer.player.
//    }
//}

public class TestPlayer {
    public static Player player = new Player();
    public static void main(String[] args) throws IOException, LineUnavailableException {
        Playah playah = new Playah();
        playah.decode("music.flac");
    }
}
