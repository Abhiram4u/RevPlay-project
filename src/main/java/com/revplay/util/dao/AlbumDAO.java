package com.revplay.util.dao;


import com.revplay.util.DBConnection;
import com.revplay.util.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    // Create Ablum
    public boolean createAlbum(Album album) {

        String sql = "INSERT INTO albums (artist_id, title, release_date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, album.getArtistId());
            ps.setString(2, album.getTitle());
            ps.setDate(3, album.getReleaseDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Failed to create album");
            return false;
        }
    }

    // View albums by atrist
    public List<Album> getAlbumsByArtist(int artistId) {

        List<Album> list = new ArrayList<>();
        String sql = "SELECT * FROM albums WHERE artist_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Album album = new Album();
                album.setAlbumId(rs.getInt("album_id"));
                album.setArtistId(rs.getInt("artist_id"));
                album.setTitle(rs.getString("title"));
                album.setReleaseDate(rs.getDate("release_date"));
                list.add(album);
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch albums");
        }
        return list;
    }

    // Update album
    public boolean updateAlbum(int albumId, String title) {

        String sql = "UPDATE albums SET title=? WHERE album_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setInt(2, albumId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    // Delete album
    public boolean deleteAlbum(int albumId, int artistId) {

        String sql = "DELETE FROM albums WHERE album_id=? AND artist_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, albumId);
            ps.setInt(2, artistId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}

