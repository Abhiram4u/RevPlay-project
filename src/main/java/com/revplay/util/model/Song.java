package com.revplay.util.model;


import java.sql.Date;

public class Song {

    private int songId;
    private int artistId;
    private Integer albumId;
    private String title;
    private String genre;
    private int duration;
    private Date releaseDate;
    private int playCount;

    public Song() {}

    public Song(int artistId, Integer albumId, String title, String genre, int duration) {
        this.artistId = artistId;
        this.albumId = albumId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    // Getters & Setters
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}

