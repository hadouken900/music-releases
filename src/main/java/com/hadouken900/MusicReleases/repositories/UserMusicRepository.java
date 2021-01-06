package com.hadouken900.MusicReleases.repositories;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.entities.UserMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMusicRepository extends JpaRepository<UserMusic, Long> {

    UserMusic findByImgAndUserId(String img, Long userId);

    List<UserMusic> findByUserId(Long id);

    List<UserMusic> findByGenreContainingIgnoreCase(String filter);
}
