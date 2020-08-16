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
import tech.laureanray.ui.elements.MenuToolbar;
import tech.laureanray.ui.WindowManager;

public class Keybindings {
    public static Keybindings instance;

    private Keybindings(Scene scene) {
        scene.setOnKeyReleased(keyEvent -> {
            System.out.println(keyEvent.getCode());
            if (keyEvent.getCode().equals(KeyCode.ALT)) {
                MenuToolbar toolbar = MenuToolbar.getInstance();
                toolbar.setVisible(!toolbar.isVisible());
            } else  if (keyEvent.getCode().equals(KeyCode.S)) {
                // trigger settings show
                System.out.println("Should show settings");
                WindowManager.getInstance().showSettings();
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
