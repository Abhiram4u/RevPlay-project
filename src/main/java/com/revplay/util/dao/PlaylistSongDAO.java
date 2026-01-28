package com.revplay.util.dao;

import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlaylistSongDAO {

    public boolean addSongToPlaylist(int playlistId, int songId) {

        String sql = "INSERT INTO playlist_songs(playlist_id, song_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Song already exists or error");
            return false;
        }
    }

    public boolean removeSongFromPlaylist(int playlistId, int songId) {

        String sql = "DELETE FROM playlist_songs WHERE playlist_id=? AND song_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}
