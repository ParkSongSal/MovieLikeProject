package com.example.movielikeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.movielikeproject.Adapter.MovieRecyclerAdapter;
import com.example.movielikeproject.Model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Movie> mMovieList;

    private RecyclerView mMemoRecyclerview;

    private MovieRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMemoRecyclerview = (RecyclerView) findViewById(R.id.memo_recycler);

        //애니메이션 커스터마이징 참고용 mMemoRecyclerview.setItemAnimator();
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setChangeDuration(1000);
        mMemoRecyclerview.setItemAnimator(animator);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivityForResult(new Intent(MainActivity.this, MemoActivity.class), REQUEST_CODE_NEW_MEMO);
            }
        });

        Movie movie = new Movie("변호인","드라마,실화","실화라 그런지 더 감명깊게 봤네요.","2021-12-06");


        mMovieList = new ArrayList<Movie>();
        // dummyData
        mMovieList.add(movie);
        movie = new Movie("도망자","스릴러","정말 긴장감이 장난 아니네요.","2021-12-07");
        mMovieList.add(movie);
        // 데이터
        //mMovieList = mMemoFacade.getMemoList();
        // 어댑터
        mAdapter = new MovieRecyclerAdapter(this,mMovieList);

        mMemoRecyclerview.setAdapter(mAdapter);
    }
}