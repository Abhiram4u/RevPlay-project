package com.revplay.util.dao;


import com.revplay.util.model.Song;
import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {

    // keyword song search
    public List<Song> searchSongs(String keyword) {

        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE title LIKE ? OR genre LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song song = new Song();
                song.setSongId(rs.getInt("song_id"));
                song.setTitle(rs.getString("title"));
                song.setGenre(rs.getString("genre"));
                song.setPlayCount(rs.getInt("play_count"));
                songs.add(song);
            }
        } catch (Exception e) {
            System.out.println("Search failed");
        }
        return songs;
    }
}
