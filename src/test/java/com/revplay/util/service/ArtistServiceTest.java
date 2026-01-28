package com.revplay.util.service;

import com.revplay.util.dao.ArtistDAO;
import com.revplay.util.model.Artist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtistServiceTest {

    @Mock
    private ArtistDAO artistDAO;

    @InjectMocks
    private ArtistService artistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterArtist_Success() {

        when(artistDAO.registerArtist(any(Artist.class)))
                .thenReturn(true);

        boolean result = artistService
                .registerArtist("John", "john@mail.com", "1234", "Rock");

        assertTrue(result);
        verify(artistDAO, times(1))
                .registerArtist(any(Artist.class));
    }

    @Test
    void testLoginArtist_Success() {

        Artist artist = new Artist();
        artist.setName("John");

        when(artistDAO.loginArtist("john@mail.com", "1234"))
                .thenReturn(artist);

        Artist result = artistService
                .loginArtist("john@mail.com", "1234");

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testLoginArtist_Failure() {

        when(artistDAO.loginArtist(anyString(), anyString()))
                .thenReturn(null);

        Artist result = artistService
                .loginArtist("wrong@mail.com", "wrong");

        assertNull(result);
    }
}
