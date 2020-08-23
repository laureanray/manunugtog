/**
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/8/20
 */

package tech.laureanray.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Track {
    private String title;
    private List<String> artists;
    private String album;
    private byte[] albumArt;
    private int durationInSeconds;
    private String durationReadable;
    private String format;
    private int bitrate;
    private int bitDepth;

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

    public void setAlbum(String album) {
        this.album = album;
    }

    public byte[] getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(byte[] albumArt) {
        this.albumArt = albumArt;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getDurationReadable() {
        int minutes = this.durationInSeconds / 60;
        int remain = this.durationInSeconds - (minutes * 60);

        return minutes + ":" + remain;
    }

    public void setDurationReadable(String durationReadable) {
        this.durationReadable = durationReadable;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getBitDepth() {
        return bitDepth;
    }

    public void setBitDepth(int bitDepth) {
        this.bitDepth = bitDepth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return getDurationInSeconds() == track.getDurationInSeconds() &&
                getBitrate() == track.getBitrate() &&
                getBitDepth() == track.getBitDepth() &&
                getTitle().equals(track.getTitle()) &&
                getArtists().equals(track.getArtists()) &&
                getAlbum().equals(track.getAlbum()) &&
                Arrays.equals(getAlbumArt(), track.getAlbumArt()) &&
                getDurationReadable().equals(track.getDurationReadable()) &&
                getFormat().equals(track.getFormat());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getTitle(), getArtists(), getAlbum(), getDurationInSeconds(), getDurationReadable(), getFormat(), getBitrate(), getBitDepth());
        result = 31 * result + Arrays.hashCode(getAlbumArt());
        return result;
    }

    public Track() {
        this.title = null;
        this.artists = new ArrayList<>();
        this.album = null;
        this.albumArt = null;
        this.durationInSeconds = 0;
        this.durationReadable = null;
        this.format = null;
        this.bitrate = 0;
        this.bitDepth = 0;
    }
}
