/**
 * ThreadManager
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/16/20
 */

package tech.laureanray.threads;

public class ThreadManager {
    private static LoadMusicThread loadMusicThread;

    public static void loadMusic(ThreadManagerCallback callback) {
        loadMusicThread.run();
    }
}
