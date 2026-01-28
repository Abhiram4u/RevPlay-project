package com.revplay.util.service;

import com.revplay.util.dao.HistoryDAO;
import com.revplay.util.dao.SongDAO;
import com.revplay.util.model.Song;

import java.util.List;

public class HistoryService {

    private HistoryDAO historyDAO = new HistoryDAO();
    private SongDAO songDAO = new SongDAO();

    public void recordPlay(int userId, Song song) {
        historyDAO.saveHistory(userId, song.getSongId());
        songDAO.incrementPlayCount(song.getSongId());
    }

    public List<Song> viewHistory(int userId) {
        return historyDAO.getListeningHistory(userId);
    }

    public List<Song> viewRecentlyPlayed(int userId) {
        return historyDAO.getRecentlyPlayed(userId);
    }
}

