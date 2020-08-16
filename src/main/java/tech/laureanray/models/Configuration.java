/**
 * Configuration
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/14/20
 */

package tech.laureanray.models;

import tech.laureanray.ui.strings.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Configuration {
    private List<String> targetDirectories;
    private String defaultView;
    private int positionX;
    private int positionY;

    public Configuration() {
        this.targetDirectories = new ArrayList<>();
        this.defaultView = UI.ALBUMS;
        this.positionX = 0;
        this.positionY = 0;
    }

    public List<String> getTargetDirectories() {
        return targetDirectories;
    }

    public void setTargetDirectories(List<String> targetDirectories) {
        this.targetDirectories = targetDirectories;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Configuration)) return false;
        Configuration that = (Configuration) o;
        return getPositionX() == that.getPositionX() &&
                getPositionY() == that.getPositionY() &&
                Objects.equals(getTargetDirectories(), that.getTargetDirectories()) &&
                Objects.equals(getDefaultView(), that.getDefaultView());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTargetDirectories(), getDefaultView(), getPositionX(), getPositionY());
    }
}
