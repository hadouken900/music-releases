package com.hadouken900.MusicReleases.repositories;

import com.hadouken900.MusicReleases.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
