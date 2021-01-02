package com.hadouken900.MusicReleases.entities;

public class Album {

    private String date;
    private String img;
    private String artist;
    private String albumName;
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
        return "[" + artist+ " - " + albumName + ": "+year+", "+ date + ", " + img + ", " + genre+ "]";
    }
}
