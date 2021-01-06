package com.hadouken900.MusicReleases.services;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.entities.UserMusic;
import com.hadouken900.MusicReleases.repositories.UserMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMusicService {
    @Autowired
    UserMusicRepository userMusicRepository;

    public void saveAlbum(UserMusic album){
        userMusicRepository.save(album);
    }

    public UserMusic findByImageAndUserId(String img, Long userId) {
        return userMusicRepository.findByImgAndUserId(img, userId);
    }

    public UserMusic createUserMusicFromAlbumAndUserId(Album album, Long userId){
        return new UserMusic(
                userId,
                album.getAlbumName(),
                album.getDate(),
                album.getImg(),
                album.getArtist(),
                album.getYear(),
                album.getGenre());
    }

    public List<UserMusic> findAllById(Long id){
        return userMusicRepository.findByUserId(id);
    }

    public void deleteAlbum(Long id) {
        userMusicRepository.deleteById(id);
    }

    public List<UserMusic> getAlbumsByFilteredGenre(String filter){
        return userMusicRepository.findByGenreContainingIgnoreCase(filter);
    }

}
