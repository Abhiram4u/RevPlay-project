package com.revplay.util.dao;


import com.revplay.util.model.Artist;
import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistDAO {

    public boolean registerArtist(Artist artist) {

        String sql = "INSERT INTO artists(name, email, password, genre) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, artist.getName());
            ps.setString(2, artist.getEmail());
            ps.setString(3, artist.getPassword());
            ps.setString(4, artist.getGenre());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Artist registration failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateArtistProfile(int artistId, String bio, String genre, String socialLinks) {

        String sql = "UPDATE artists SET bio=?, genre=?, social_links=? WHERE artist_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bio);
            ps.setString(2, genre);
            ps.setString(3, socialLinks);
            ps.setInt(4, artistId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Failed to update artist profile");
            return false;
        }
    }

    public Artist loginArtist(String email, String password) {

        String sql = "SELECT * FROM artists WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Artist artist = new Artist();
                artist.setArtistId(rs.getInt("artist_id"));
                artist.setName(rs.getString("name"));
                artist.setEmail(rs.getString("email"));
                artist.setGenre(rs.getString("genre"));
                return artist;
            }

        } catch (SQLException e) {
            System.out.println("Artist login failed: " + e.getMessage());
        }
        return null;
    }
}
