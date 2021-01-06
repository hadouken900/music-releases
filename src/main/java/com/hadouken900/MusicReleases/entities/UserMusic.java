package com.hadouken900.MusicReleases.entities;

import javax.persistence.*;

@Entity
@Table(name="user_music")
public class UserMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UserMusic(Long userId, String albumName, String date, String img, String artist, String year, String genre) {
        this.userId = userId;
        this.albumName = albumName;
        this.date = date;
        this.img = img;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
    }

    @Column(name="user_id")
    private Long userId;

    @Column(name="album")
    private String albumName;
    private String date;
    private String img;
    private String artist;
    private String year;
    private String genre;

    public UserMusic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
