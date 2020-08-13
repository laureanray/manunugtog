/**
 * Keybindings
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/13/20
 */

package tech.laureanray.utils;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import tech.laureanray.ui.MenuToolbar;

public class Keybindings {
    public static Keybindings instance;

    private Keybindings(Scene scene) {
        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ALT)) {
                MenuToolbar toolbar = MenuToolbar.getInstance();
                toolbar.setVisible(!toolbar.isVisible());
            }
        });
    }

    public static Keybindings initialize(Scene scene) {
        if (instance == null) {
            if (scene == null) {
                return null;
            }
            instance = new Keybindings(scene);
        }

        return instance;
    }
}
