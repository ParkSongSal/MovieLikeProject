
package com.example.movielikeproject.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static MovieDBHelper sInstance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Movie.db";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME;

    public static MovieDBHelper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new MovieDBHelper(context);
        }
        return sInstance;
    }
    //생성자
    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //DB를 처음으로 사용할 때
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieContract.SQL_CREATE_MOVIE_TABLE);
    }

    // DB 업그레이드시
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

