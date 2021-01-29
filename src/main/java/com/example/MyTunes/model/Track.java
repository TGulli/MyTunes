package com.example.MyTunes.model;

public class Track {
    private int trackId;
    private String trackName;
    private String artistName;
    private String genre;
    private String albumName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Track(int trackId, String trackName) {
        this.trackId = trackId;
        this.trackName = trackName;
    }

    public Track(String trackName, String artistName, String albumName, String genre) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.genre = genre;
        this.albumName = albumName;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
