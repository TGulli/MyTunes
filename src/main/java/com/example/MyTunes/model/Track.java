package com.example.MyTunes.model;

/**
 * POJO for Track
 */


public class Track {
    private int trackId;
    private String trackName;
    private String artistName;
    private String genre;
    private String albumName;

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

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbumName() {
        return albumName;
    }
}
