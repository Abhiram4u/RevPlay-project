package com.revplay.util.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revplay.util.model.Song;
import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    private static final Logger logger = LogManager.getLogger(SongDAO.class);


    public void incrementPlayCount(int songId) {

        String sql = "UPDATE songs SET play_count = play_count + 1 WHERE song_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Failed to update play count");
        }
    }


    public boolean deleteSong(int songId, int artistId) {

        String sql = "DELETE FROM songs WHERE song_id=? AND artist_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ps.setInt(2, artistId);

            logger.info("Song deleted | SongId={} | ArtistId={}", songId, artistId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Failed to delete song | SongId={} | ArtistId={}",
                    songId, artistId, e);
//            System.out.println("Failed to delete song");
            return false;
        }
    }

    public boolean updateSong(int songId, String title, String genre) {

        String sql = "UPDATE songs SET title=?, genre=? WHERE song_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setInt(3, songId);

            logger.info("Song updated | SongId={} | NewTitle={}", songId, title);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Failed to update song | SongId={}", songId, e);
            return false;
        }
    }

    // Upload song
    public boolean addSong(Song song) {

        String sql = "INSERT INTO songs(artist_id, album_id, title, genre, duration) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, song.getArtistId());
            if (song.getAlbumId() != null)
                ps.setInt(2, song.getAlbumId());
            else
                ps.setNull(2, java.sql.Types.INTEGER);

            ps.setString(3, song.getTitle());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getDuration());

            logger.info("Song uploaded successfully | ArtistId={} | Title={}",
                    song.getArtistId(), song.getTitle());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
//            System.out.println("Song upload failed: " + e.getMessage());
            logger.error("Song upload failed | ArtistId={} | Title={}",
                    song.getArtistId(), song.getTitle(), e);

            return false;
        }
    }

    public List<Song> getSongsByArtist(int artistId) {

        List<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE artist_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setPlayCount(rs.getInt("play_count"));
                list.add(s);
            }
        } catch (Exception e) {}

        return list;
    }

    // View all songs
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Song song = new Song();
                song.setSongId(rs.getInt("song_id"));
                song.setTitle(rs.getString("title"));
                song.setGenre(rs.getString("genre"));
                song.setDuration(rs.getInt("duration"));
                song.setPlayCount(rs.getInt("play_count"));
                songs.add(song);
            }
            logger.info("Fetched all songs successfully | Count={}", songs.size());

        } catch (Exception e) {
//            System.out.println("Fetch songs failed");
            logger.error("Failed to fetch songs", e);


        }

        return songs;
    }
}

