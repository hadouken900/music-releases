package com.hadouken900.MusicReleases.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="user_music")
public class UserMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="album")
    private String albumName;
    private String date;
    private String img;
    private String artist;
    private String year;
    private String genre;

    public UserMusic(Long userId, String albumName, String date, String img, String artist, String year, String genre) {
        this.userId = userId;
        this.albumName = albumName;
        this.date = date;
        this.img = img;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
    }
}
