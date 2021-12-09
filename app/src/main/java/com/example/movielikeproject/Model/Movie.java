package com.example.movielikeproject.Model;

import java.io.Serializable;

/**
 * Created by user on 2018-01-01.
 */

public class Movie implements Serializable {

    private long id;

    // 영화 제목
    private String title;

    // 영화 장르
    private String movieKind;

    // 영화 관람 한줄평
    private String contents;

    // 영화 관람일자
    private String movieDate;

    public Movie(String title, String movieKind, String contents, String movieDate) {
        this.title = title;
        this.movieKind = movieKind;
        this.contents = contents;
        this.movieDate = movieDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieKind() {
        return movieKind;
    }

    public void setMovieKind(String movieKind) {
        this.movieKind = movieKind;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }
}

