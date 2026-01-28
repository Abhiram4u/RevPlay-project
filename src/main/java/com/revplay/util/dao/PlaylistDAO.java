package com.revplay.util.dao;

import com.revplay.util.model.Playlist;
import com.revplay.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {


    public boolean createPlaylist(Playlist playlist) {

        String sql = "INSERT INTO playlists(user_id, name, description, is_public) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlist.getUserId());
            ps.setString(2, playlist.getName());
            ps.setString(3, playlist.getDescription());
            ps.setBoolean(4, playlist.isPublic());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Failed to create playlist");
            return false;
        }
    }

    // View playlists
    public List<Playlist> getUserPlaylists(int userId) {

        List<Playlist> list = new ArrayList<>();
        String sql = "SELECT * FROM playlists WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Playlist p = new Playlist();
                p.setPlaylistId(rs.getInt("playlist_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPublic(rs.getBoolean("is_public"));
                list.add(p);
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch playlists");
        }
        return list;
    }


    public boolean updatePlaylist(int playlistId, String name, String desc, boolean isPublic) {

        String sql = "UPDATE playlists SET name=?, description=?, is_public=? WHERE playlist_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setBoolean(3, isPublic);
            ps.setInt(4, playlistId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    // Delete playlist
    public boolean deletePlaylist(int playlistId) {

        String sql = "DELETE FROM playlists WHERE playlist_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    // View public playlists
    public List<Playlist> getPublicPlaylists() {

        List<Playlist> list = new ArrayList<>();
        String sql = "SELECT * FROM playlists WHERE is_public = true";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Playlist p = new Playlist();
                p.setPlaylistId(rs.getInt("playlist_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch public playlists");
        }
        return list;
    }
}
