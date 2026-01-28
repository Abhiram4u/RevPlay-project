package com.revplay.util.service;


import com.revplay.util.dao.FavoriteDAO;
import com.revplay.util.model.Song;

import java.util.List;

public class FavoriteService {

    private FavoriteDAO favoriteDAO = new FavoriteDAO();

    public boolean addToFavorites(int userId, int songId) {
        return favoriteDAO.addFavorite(userId, songId);
    }

    public List<Song> viewFavorites(int userId) {
        return favoriteDAO.getFavoriteSongs(userId);
    }

    public List<String> viewUsersWhoFavoritedSong(int songId) {
        return favoriteDAO.getUsersWhoFavoritedSong(songId);
    }
}

