package com.example.movielikeproject.Model;

import java.io.Serializable;

/**
 * Created by user on 2018-01-01.
 */

public class Movie implements Serializable {

    private long id;

    // 영화 제목
    private String movieName;

    // 영화 감독
    private String director;

    // 영화 장르
    private String genre;

    // 영화 배우
    private String actor;

    // 영화 관람 한줄평
    private String review;

    // 영화 관람일자
    private String movieDate;

    // 영화 수정일자
    private String updateDate;


    public Movie(String movieName, String director, String genre, String actor, String review, String movieDate, String updateDate) {
        this.movieName = movieName;
        this.director = director;
        this.genre = genre;
        this.actor = actor;
        this.review = review;
        this.movieDate = movieDate;
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", actor='" + actor + '\'' +
                ", review='" + review + '\'' +
                ", movieDate='" + movieDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}

