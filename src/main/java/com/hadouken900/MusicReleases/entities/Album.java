package com.hadouken900.MusicReleases.entities;

import javax.persistence.*;

@Entity
@Table(name="albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="date")
    private String date;
    @Column(name="img")
    private String img;
    @Column(name="artist")
    private String artist;

    @Column(name="album")
    private String albumName;

    @Column(name="year")
    private String year;

    @Column(name="genre")
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album(Long id, String date, String img, String artist, String albumName, String year, String genre) {
        this.id = id;
        this.date = date;
        this.img = img;
        this.artist = artist;
        this.albumName = albumName;
        this.year = year;
        this.genre = genre;
    }

    public Album() {
    }

    public String getDate() {
        return date;
    }

    public String getImg() {
        return img;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return id + "[" + artist+ " - " + albumName + ": "+year+", "+ date + ", " + img + ", " + genre+ "]";
    }
}
