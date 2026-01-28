package com.revplay.util.dao;



import com.revplay.util.model.Song;
import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {


    public void saveHistory(int userId, int songId) {

        String sql = "INSERT INTO listening_history(user_id, song_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Failed to save listening history");
        }
    }


    public List<Song> getListeningHistory(int userId) {

        List<Song> list = new ArrayList<>();

        String sql =
                "SELECT s.song_id, s.title, s.genre, h.played_at " +
                        "FROM songs s " +
                        "JOIN listening_history h ON s.song_id = h.song_id " +
                        "WHERE h.user_id = ? " +
                        "ORDER BY h.played_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch history");
        }

        return list;
    }

    // Recently played
    public List<Song> getRecentlyPlayed(int userId) {

        List<Song> list = new ArrayList<>();

        String sql =
                "SELECT s.song_id, s.title " +
                        "FROM songs s " +
                        "JOIN listening_history h ON s.song_id = h.song_id " +
                        "WHERE h.user_id = ? " +
                        "ORDER BY h.played_at DESC LIMIT 5";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch recently played");
        }

        return list;
    }
}
