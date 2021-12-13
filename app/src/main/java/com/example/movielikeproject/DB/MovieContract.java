
package com.example.movielikeproject.DB;

import android.provider.BaseColumns;

//영화가 좋아 스키마(구조)
public final class MovieContract {


    /* CREATE TABLE movie
    * (
    *   _id INTEGER PRIMARY KEY AUTOINCREMENT,
    *   movieName TEXT,
    *   director TEXT,
    *   actor TEXT,
    *   genre TEXT,
    *   rating TEXT,
    *   review TEXT,
    *   regDate TEXT,
    *   updateDate TEXT
    * );
    * */

    public static final String SQL_CREATE_MOVIE_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "%s TEXT," +
                            "%s TEXT," +
                            "%s TEXT," +
                            "%s TEXT," +
                            "%s TEXT," +
                            "%s TEXT," +
                            "%s TEXT," +
                            "%s TEXT);",
                    MovieEntry.TABLE_NAME,
                    MovieEntry._ID,
                    MovieEntry.COLUMN_NAME_MOVIENAME,
                    MovieEntry.COLUMN_NAME_DIRECTOR,
                    MovieEntry.COLUMN_NAME_ACTOR,
                    MovieEntry.COLUMN_NAME_GENRE,
                    MovieEntry.COLUMN_NAME_RATING,
                    MovieEntry.COLUMN_NAME_REVIEW,
                    MovieEntry.COLUMN_NAME_REGDATE,
                    MovieEntry.COLUMN_NAME_UPDATEDATE);

    private MovieContract() {

    }
    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_NAME_MOVIENAME = "movieName";
        public static final String COLUMN_NAME_DIRECTOR = "director";
        public static final String COLUMN_NAME_ACTOR = "actor";
        public static final String COLUMN_NAME_GENRE = "genre";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_REVIEW = "review";
        public static final String COLUMN_NAME_REGDATE = "regDate";
        public static final String COLUMN_NAME_UPDATEDATE = "updateDate";
    }
}

