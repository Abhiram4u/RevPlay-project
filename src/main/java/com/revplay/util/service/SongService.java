package com.revplay.util.service;



import com.revplay.util.dao.SongDAO;
import com.revplay.util.model.Song;

import java.util.List;

public class SongService {

    private SongDAO songDAO = new SongDAO();

    public boolean updateSong(int songId, String title, String genre) {
        return songDAO.updateSong(songId, title, genre);
    }

    public List<Song> viewMySongs(int artistId) {
        return songDAO.getSongsByArtist(artistId);
    }

    public boolean deleteSong(int songId, int artistId) {
        return songDAO.deleteSong(songId, artistId);
    }

    public boolean uploadSong(int artistId, Integer albumId, String title, String genre, int duration) {

        if (title.isEmpty()) {
            System.out.println("Song title required");
            return false;
        }

        Song song = new Song(artistId, albumId, title, genre, duration);
        return songDAO.addSong(song);
    }

    public List<Song> viewAllSongs() {
        return songDAO.getAllSongs();
    }
}

