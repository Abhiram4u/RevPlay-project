package com.revplay.util.dao;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.revplay.util.model.Song;
import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDAO {

    // Add song to favorites
    public boolean addFavorite(int userId, int songId) {

        String sql = "INSERT INTO favorites(user_id, song_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Song already in favorites or error occurred.");
            return false;
        }
    }

    // View user's favorite songs
    public List<Song> getFavoriteSongs(int userId) {

        List<Song> list = new ArrayList<>();

        String sql =
                "SELECT s.song_id, s.title, s.genre " +
                        "FROM songs s " +
                        "JOIN favorites f ON s.song_id = f.song_id " +
                        "WHERE f.user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song song = new Song();
                song.setSongId(rs.getInt("song_id"));
                song.setTitle(rs.getString("title"));
                song.setGenre(rs.getString("genre"));
                list.add(song);
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch favorites");
        }

        return list;
    }

    // Artist: see users who favorited songs
    public List<String> getUsersWhoFavoritedSong(int songId) {

        List<String> users = new ArrayList<>();

        String sql =
                "SELECT u.name " +
                        "FROM users u " +
                        "JOIN favorites f ON u.user_id = f.user_id " +
                        "WHERE f.song_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(rs.getString("name"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching users");
        }

        return users;
    }
}

