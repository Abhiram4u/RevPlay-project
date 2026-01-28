package com.revplay.util.service;



import com.revplay.util.dao.ArtistDAO;
import com.revplay.util.model.Artist;


public class ArtistService {
    private ArtistDAO artistDAO = new ArtistDAO();

    public boolean updateProfile(int artistId, String bio, String genre, String socialLinks) {
        return artistDAO.updateArtistProfile(artistId, bio, genre, socialLinks);
    }

    public boolean registerArtist(String name, String email, String password, String genre) {

        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and Email required");
            return false;
        }

        Artist artist = new Artist(name, email, password, genre);
        return artistDAO.registerArtist(artist);
    }

    public Artist loginArtist(String email, String password) {
        return artistDAO.loginArtist(email, password);
    }
}


