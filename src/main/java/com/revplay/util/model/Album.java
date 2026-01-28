package com.revplay.util.model;

import java.sql.Date;

public class Album {

    private int albumId;
    private int artistId;
    private String title;
    private Date releaseDate;

    public Album() {}

    public Album(int artistId, String title, Date releaseDate) {
        this.artistId = artistId;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    // Getters & Setters
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}

