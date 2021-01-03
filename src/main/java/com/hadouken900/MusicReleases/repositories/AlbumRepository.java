package com.hadouken900.MusicReleases.repositories;

import com.hadouken900.MusicReleases.entities.Album;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByGenreContainingIgnoreCase(String filter);
}
