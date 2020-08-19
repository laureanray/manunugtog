/**
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.models;

import java.util.List;
import java.util.Objects;

public class Track {
    private String title;
    private List<String> artists;
    private String album;
    private Byte[] albumArt;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getAlbum() {
        return album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return Objects.equals(getTitle(), track.getTitle()) &&
                Objects.equals(getArtists(), track.getArtists()) &&
                Objects.equals(getAlbum(), track.getAlbum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getArtists(), getAlbum());
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Track(String title, List<String> artists, String album) {
        this.title = title;
        this.artists = artists;
        this.album = album;
    }
}
