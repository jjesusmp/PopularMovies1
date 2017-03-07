package com.example.android.popularmovies1.model;

import java.io.Serializable;

/**
 * Created by jjesusmp
 */

public class MovieDto implements Serializable {
    private String title;

    private String image;

    private String sypnosis;

    private String user_rating;

    private String release_date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSypnosis() {
        return sypnosis;
    }

    public void setSypnosis(String sypnosis) {
        this.sypnosis = sypnosis;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", sypnosis='" + sypnosis + '\'' +
                ", user_rating='" + user_rating + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
