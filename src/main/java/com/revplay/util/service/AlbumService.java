package com.revplay.util.service;

import com.revplay.util.dao.AlbumDAO;
import com.revplay.util.model.Album;

import java.sql.Date;
import java.util.List;

public class AlbumService {

    private AlbumDAO albumDAO = new AlbumDAO();

    public boolean createAlbum(int artistId, String title, Date releaseDate) {
        Album album = new Album(artistId, title, releaseDate);
        return albumDAO.createAlbum(album);
    }

    public List<Album> viewMyAlbums(int artistId) {
        return albumDAO.getAlbumsByArtist(artistId);
    }

    public boolean updateAlbum(int albumId, String title) {
        return albumDAO.updateAlbum(albumId, title);
    }

    public boolean deleteAlbum(int albumId, int artistId) {
        return albumDAO.deleteAlbum(albumId, artistId);
    }
}
