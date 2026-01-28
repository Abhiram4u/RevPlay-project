package com.revplay.util.service;

import com.revplay.util.dao.PlaylistDAO;
import com.revplay.util.dao.PlaylistSongDAO;
import com.revplay.util.model.Playlist;

import java.util.List;

public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private PlaylistSongDAO playlistSongDAO = new PlaylistSongDAO();

    public boolean createPlaylist(int userId, String name, String desc, boolean isPublic) {
        Playlist p = new Playlist(userId, name, isPublic);
        p.setDescription(desc);
        return playlistDAO.createPlaylist(p);
    }

    public List<Playlist> viewMyPlaylists(int userId) {
        return playlistDAO.getUserPlaylists(userId);
    }

    public boolean updatePlaylist(int playlistId, String name, String desc, boolean isPublic) {
        return playlistDAO.updatePlaylist(playlistId, name, desc, isPublic);
    }

    public boolean deletePlaylist(int playlistId) {
        return playlistDAO.deletePlaylist(playlistId);
    }

    public boolean addSong(int playlistId, int songId) {
        return playlistSongDAO.addSongToPlaylist(playlistId, songId);
    }

    public boolean removeSong(int playlistId, int songId) {
        return playlistSongDAO.removeSongFromPlaylist(playlistId, songId);
    }

    public List<Playlist> viewPublicPlaylists() {
        return playlistDAO.getPublicPlaylists();
    }
}
