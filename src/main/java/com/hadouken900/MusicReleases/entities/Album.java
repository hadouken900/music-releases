package com.hadouken900.MusicReleases.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="releases")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="album")
    private String albumName;
    private String date;
    private String img;
    private String artist;
    private String year;
    private String genre;


    public Album(String date, String img, String artist, String albumName, String year, String genre) {
        this.date = date;
        this.img = img;
        this.artist = artist;
        this.albumName = albumName;
        this.year = year;
        this.genre = genre;
    }

    public Album(Long id, String albumName, String date, String img, String artist, String year, String genre) {
        this.id = id;
        this.albumName = albumName;
        this.date = date;
        this.img = img;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "[" + artist+ " - " + albumName + ": "+year+", "+ date + ", " + img + ", " + genre+ "]";
    }
}
