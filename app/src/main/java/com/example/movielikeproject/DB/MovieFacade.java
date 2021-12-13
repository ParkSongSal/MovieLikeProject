package com.example.movielikeproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.movielikeproject.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieFacade {

    private MovieDBHelper mDbHelper;

    public MovieFacade(Context context) {
        mDbHelper = new MovieDBHelper(context);
    }

    /*
     * 영화를 추가(삽입)한다
     * movieName 영화제목
     * director 영화 감독
     * genre 장르
     * actor 배우
     * rating 평점
     * review 한줄평
     * movieDate 관람일자
     * updateDate 수정일자
     * return 추가된 row  의 id, 만약 에러가 발생되면 -1
     * */
    public long insert(String movieName,
                       String director,
                       String genre,
                       String actor,
                       String rating,
                       String review,
                       String movieDate,
                       String updateDate) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COLUMN_NAME_MOVIENAME, movieName);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_DIRECTOR, director);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_GENRE, genre);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_ACTOR, actor);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_RATING, rating);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_REVIEW, review);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_REGDATE, movieDate);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_UPDATEDATE, updateDate);

        long newRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);

        return newRowId;
    }


    /*
     * 전체 영화 리스트
     * @return 전체 영화
     * */
    public List<Movie> getMovieList(String selection,
                                   String[] selectionArgs,
                                   String groupBy,
                                   String having,
                                   String orderBy) {

        ArrayList<Movie> movieArrayList = new ArrayList<>();
        // DB에서 읽어오기
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // ORDER BY _ID DESC  같음
        String order = MovieContract.MovieEntry._ID + " DESC";

        Cursor c = db.query(
                MovieContract.MovieEntry.TABLE_NAME,      // table name
                null,                           //columns to return 리턴할 컬럼들
                selection,                             //columns for the Where clause 조건
                selectionArgs,                           //values for the
                groupBy,                                //don`t group the rows
                having,                                  //don`t filter by row group
                orderBy == null ? order : orderBy        // sort order
        );

        if (c != null) {
            // 커서를 Movie 로 변환
            //c.moveToFirst(); 커서를 처음 항목으로 이동
            while (c.moveToNext()) {
                String movieName = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_MOVIENAME));
                String director = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_DIRECTOR));
                String genre = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_GENRE));
                String actor = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_ACTOR));
                String rating = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_RATING));
                String review = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_REVIEW));
                String regDate = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_REGDATE));
                String updateDate = c.getString(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_UPDATEDATE));
                long id = c.getLong(
                        c.getColumnIndexOrThrow(MovieContract.MovieEntry._ID));
                Movie movie = new Movie(movieName, director, genre,actor,rating,review,regDate,updateDate);
                movie.setId(id);
                movieArrayList.add(movie);
            }
            // 커서 닫기
            c.close();
        }
        return movieArrayList;
    }

    /**
     * 전체 영화 리스트
     *
     * @return 전체 메모
     */
    public List<Movie> getMovieList() {
        return getMovieList(null, null, null, null, null);
    }


    /*
     * 영화 삭제
     * param id 삭제할 영화 id
     * return 삭제된 행의수 수
     * */
    public int delete(long id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int deleted = db.delete(MovieContract.MovieEntry.TABLE_NAME,
                "_id=" + id,
                null);
        return deleted;
    }

    /*
    * 영화 수정
    * return 수정된 행의
    * */
    public int update(long id,
                      String movieName,
                      String director,
                      String genre,
                      String actor,
                      String rating,
                      String review,
                      String movieDate,
                      String updateDate) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COLUMN_NAME_MOVIENAME, movieName);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_DIRECTOR, director);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_GENRE, genre);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_ACTOR, actor);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_RATING, rating);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_REVIEW, review);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_REGDATE, movieDate);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_UPDATEDATE, updateDate);

        int count = db.update(
                MovieContract.MovieEntry.TABLE_NAME,
                values,
                MovieContract.MovieEntry._ID + " = " + id,
                null);
        return count;
    }


}
