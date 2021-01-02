package com.hadouken900.MusicReleases.repositories;

import com.hadouken900.MusicReleases.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
