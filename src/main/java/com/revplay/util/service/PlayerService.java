package com.revplay.util.service;


import com.revplay.util.model.Song;

public class PlayerService {

    private Song currentSong;

    public void play(Song song) {
        currentSong = song;
        System.out.println("Playing: " + song.getTitle());
    }

    public void pause() {
        if (currentSong != null) {
            System.out.println("Paused: " + currentSong.getTitle());
        } else {
            System.out.println("No song is playing");
        }
    }

    public void skip() {
        if (currentSong != null) {
            System.out.println("Skipped: " + currentSong.getTitle());
            currentSong = null;
        } else {
            System.out.println("No song to skip");
        }
    }

    public void repeat() {
        if (currentSong != null) {
            System.out.println("Repeating: " + currentSong.getTitle());
        } else {
            System.out.println("No song to repeat");
        }
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}

