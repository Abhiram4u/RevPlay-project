package com.revplay.util.service;


import com.revplay.util.dao.SearchDAO;
import com.revplay.util.model.Song;

import java.util.List;

public class SearchService {

    private SearchDAO searchDAO = new SearchDAO();

    public List<Song> searchSongs(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            System.out.println("Search keyword required");
            return null;
        }
        return searchDAO.searchSongs(keyword);
    }
}

