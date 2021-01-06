package com.hadouken900.MusicReleases.services;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public List<Album> getAlbumsByFilteredGenre(String filter){
        return albumRepository.findByGenreContainingIgnoreCase(filter);
    }

    public void saveAllAlbums(List<Album> list) {
        albumRepository.saveAll(list);
    }

    public void clearTable() {
        albumRepository.deleteAll();
    }

    public void saveAlbum(Album album){
        albumRepository.save(album);
    }

    public Album getAlbumById(Long id) {
        return albumRepository.getOne(id);
    }
}
