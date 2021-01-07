package com.hadouken900.MusicReleases.services;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.repositories.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlbumServiceTest {
    @Autowired
    AlbumService albumService;
    @MockBean
    AlbumRepository albumRepository;
    List<Album> albums = new ArrayList<>(Arrays.asList(
            new Album(1L,"", "","","","","rock"),
            new Album(2L,"", "","","","","rap"),
            new Album(3L,"", "","","","","jazz"),
            new Album(4L,"", "","","","","ambient")
    ));

    @Test
    void getAllAlbums() {

        Mockito.doReturn(albums).when(albumRepository).findAll();
        assertEquals(4,albumService.getAllAlbums().size());

    }

    @Test
    void getAlbumsByFilteredGenre() {
        String filter = "ambient";
        Mockito.doReturn(albums.subList(3,4)).when(albumRepository).findByGenreContainingIgnoreCase(filter);

        assertEquals(4L, albumService.getAlbumsByFilteredGenre(filter).get(0).getId());

    }


    @Test
    void getAlbumById() {
        long id = 4L;

        Mockito.doReturn(albums.stream().filter(o->o.getId()==id).findFirst().get())
                .when(albumRepository).getOne(id);

        assertEquals("ambient", albumService.getAlbumById(id).getGenre());

    }
}