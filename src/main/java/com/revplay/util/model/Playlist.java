package com.revplay.util.model;


import java.sql.Timestamp;

public class Playlist {

    private int playlistId;
    private int userId;
    private String name;
    private String description;
    private boolean isPublic;
    private Timestamp createdAt;

    public Playlist() {}

    public Playlist(int userId, String name, boolean isPublic) {
        this.userId = userId;
        this.name = name;
        this.isPublic = isPublic;
    }

    // Getters & Setters
    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

