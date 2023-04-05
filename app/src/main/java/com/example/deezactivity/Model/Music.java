package com.example.deezactivity.Model;

import java.io.Serializable;

public class Music implements Serializable {
    private String title, album, artist, cover, url, link;

    public Music(String title, String album, String artist, String cover, String url, String link) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.cover = cover;
        this.url = url;
        this.link = link;
    }

    public Music() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
