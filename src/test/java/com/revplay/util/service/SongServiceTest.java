package com.revplay.util.service;

import com.revplay.util.dao.SongDAO;
import com.revplay.util.model.Song;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SongServiceTest {

    @Mock
    private SongDAO songDAO;

    @InjectMocks
    private SongService songService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadSong_Success() {

        when(songDAO.addSong(any(Song.class)))
                .thenReturn(true);

        boolean result = songService
                .uploadSong(1, null, "My Song", "Pop", 200);

        assertTrue(result);
        verify(songDAO, times(1))
                .addSong(any(Song.class));
    }

    @Test
    void testDeleteSong_Success() {

        when(songDAO.deleteSong(1, 1))
                .thenReturn(true);

        boolean result = songService.deleteSong(1, 1);

        assertTrue(result);
    }

    @Test
    void testViewMySongs() {

        when(songDAO.getSongsByArtist(1))
                .thenReturn(List.of(new Song(), new Song()));

        List<Song> songs = songService.viewMySongs(1);

        assertEquals(2, songs.size());
    }
}
