package tech.laureanray.app;

import tech.laureanray.models.Track;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDataManager {
    /*
        TODO:
            1. Implement a system that triggers UI updates when data changes (like loading new files, files being added to library directory)

     */
    private static ApplicationDataManager instance;
    private List<Track> trackList;

    private ApplicationDataManager() {
        this.trackList = new ArrayList<>();
    }

    public static ApplicationDataManager getInstance() {
        if (instance == null) {
            instance = new ApplicationDataManager();

            /*
            TODO:
                1. Create app data file if not exits
                2. Load app data file
             */


        }
        return instance;
    }

    public static void loadData() {

    }

    public static void updateData() {

    }

    public void addTrack(Track track) {
        this.trackList.add(track);
        System.out.println("ADDED: " + track.getTitle());
    }


    public List<Track> getTracks() {
        return new ArrayList<Track>(this.trackList);
    }
}
