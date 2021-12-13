package com.example.movielikeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.movielikeproject.DB.MovieFacade;
import com.example.movielikeproject.Model.Movie;

public class MovieUpdateActivity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener callbackMethod;
    private EditText movieName_edit, director_edit, actor_edit, genre_edit, review_edit, movieDate_edit;
    private RatingBar movieRating;
    private float mRating = 0;
    private long mMovieId = -1;

    Intent intent;
    private MovieFacade mMovieFacade;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_update);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("수정");

        movieName_edit = findViewById(R.id.movieNameEdit);
        director_edit = findViewById(R.id.directorEdit);
        genre_edit = findViewById(R.id.genreEdit);
        actor_edit = findViewById(R.id.actorEdit);
        movieRating = findViewById(R.id.movieRatingBar);
        review_edit = findViewById(R.id.reviewEdit);
        movieDate_edit = findViewById(R.id.movieDateEdit);

        mMovieFacade = new MovieFacade(this);
        // 관람일자 DatePicker init
        initDateListener();
        movieDate_edit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog dialog = new DatePickerDialog(MovieUpdateActivity.this, callbackMethod, 2021,12, 7);
                    dialog.show();
                }
            }
        });
        movieRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRating = v;
            }
        });
        intent = getIntent();

        if(intent != null){
            mMovieId = intent.getLongExtra("id",-1);
            movie = (Movie) intent.getSerializableExtra("movie");

            movieName_edit.setText(movie.getMovieName());
            director_edit.setText(movie.getDirector());
            genre_edit.setText(movie.getGenre());
            actor_edit.setText(movie.getActor());

            float mRating = Float.parseFloat(movie.getRating());
            movieRating.setRating(mRating);

            review_edit.setText(movie.getReview());
            movieDate_edit.setText(movie.getMovieDate());
        }else{
            Toast.makeText(getApplicationContext(), "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void initDateListener(){
        callbackMethod = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                // month(월) 데이터
                String mMonth = "";
                switch (monthOfYear + 1){
                    case 1:
                        mMonth = "01";
                        break;
                    case 2:
                        mMonth = "02";
                        break;
                    case 3:
                        mMonth = "03";
                        break;
                    case 4:
                        mMonth = "04";
                        break;
                    case 5:
                        mMonth = "05";
                        break;
                    case 6:
                        mMonth = "06";
                        break;
                    case 7:
                        mMonth = "07";
                        break;
                    case 8:
                        mMonth = "08";
                        break;
                    case 9:
                        mMonth = "09";
                        break;
                }

                // day(일) 데이
                String mDay = "";
                switch (dayOfMonth){
                    case 1:
                        mDay = "01";
                        break;
                    case 2:
                        mDay = "02";
                        break;
                    case 3:
                        mDay = "03";
                        break;
                    case 4:
                        mDay = "04";
                        break;
                    case 5:
                        mDay = "05";
                        break;
                    case 6:
                        mDay = "06";
                        break;
                    case 7:
                        mDay = "07";
                        break;
                    case 8:
                        mDay = "08";
                        break;
                    case 9:
                        mDay = "09";
                        break;
                }
                movieDate_edit.setText(year + "년" + mMonth + "월" + mDay + "일");
                movieDate_edit.clearFocus();

            }
        };
    }

    public void onClick(View view) {
        switch (view.getId()){
            // 취소버튼
            case R.id.cancelBtn:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MovieUpdateActivity.this);
                dialog.setTitle("종료 알림")

                        .setMessage("수정 중인 영화를 저장하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                save();

                                Toast.makeText(MovieUpdateActivity.this, "저장하였습니다.", Toast.LENGTH_SHORT).show();
                            }

                        })

                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                cancel();
                                Toast.makeText(MovieUpdateActivity.this, "저장하지 않았습니다.", Toast.LENGTH_SHORT).show();

                            }

                        }).create().show();
                break;

            // 등록버튼
            case R.id.saveBtn:
                save();
                break;
            default:
                break;
        }
    }
    private void cancel() {
        setResult(RESULT_CANCELED);
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void save(){

        String movieName = movieName_edit.getText().toString();
        String director = director_edit.getText().toString();
        String genre = genre_edit.getText().toString();
        String actor = actor_edit.getText().toString();
        String rating = String.valueOf(mRating);
        String review = review_edit.getText().toString();
        String movieDate = movieDate_edit.getText().toString();

        long newRowId = mMovieFacade.update(
                mMovieId,
                movieName,
                director,
                genre,
                actor,
                rating,
                review,
                movieDate,
                movieDate);
        if(newRowId == -1){
            Toast.makeText(getApplicationContext(), "수정을 실패했습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "수정되었습니다..", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}