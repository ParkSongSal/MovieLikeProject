package com.example.movielikeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movielikeproject.Model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView movieNameTxt, genreTxt, directorTxt, actorTxt, movieDateTxt, reviewTxt;
    RatingBar movieRating;
    Intent intent;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        intent = getIntent();

        movieNameTxt = findViewById(R.id.movieNameTxt);
        genreTxt = findViewById(R.id.genreTxt);
        directorTxt = findViewById(R.id.directorTxt);
        actorTxt = findViewById(R.id.actorTxt);
        movieRating = findViewById(R.id.movieRatingBar);
        movieDateTxt = findViewById(R.id.movieDateTxt);
        reviewTxt = findViewById(R.id.reviewTxt);

        if(intent != null){
            movie = (Movie) intent.getSerializableExtra("movie");

            movieNameTxt.setText(movie.getMovieName());
            genreTxt.setText(movie.getGenre());
            directorTxt.setText(movie.getDirector());
            actorTxt.setText(movie.getActor());
            movieDateTxt.setText(movie.getMovieDate());
            reviewTxt.setText(movie.getReview());

            float mRating = Float.parseFloat(movie.getRating());
            movieRating.setRating(mRating);
        }else{
            Toast.makeText(getApplicationContext(), "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelBtn:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.updateGoBtn:
                intent = new Intent(this, MovieUpdateActivity.class);
                intent.putExtra("id",movie.getId());
                intent.putExtra("movie",movie);

                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}