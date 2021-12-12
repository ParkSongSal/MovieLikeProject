package com.example.movielikeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.movielikeproject.Adapter.MovieRecyclerAdapter;
import com.example.movielikeproject.DB.MovieContract;
import com.example.movielikeproject.DB.MovieFacade;
import com.example.movielikeproject.Model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Movie> mMovieList;

    private RecyclerView mMemoRecyclerview;

    private MovieRecyclerAdapter mAdapter;
    private MovieFacade mMovieFacade;

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
                Intent intent = new Intent(MainActivity.this, MovieWriteActivity.class);
                startActivity(intent);
                finish();

                //startActivityForResult(new Intent(MainActivity.this, MemoActivity.class), REQUEST_CODE_NEW_MEMO);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //SearchView
        SearchView searchView = (SearchView) findViewById(R.id.Search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //완료누르면
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {//변경될때마다
                // 새로운 쿼리의 결과 뿌리기
                mMovieList = mMovieFacade.getMovieList(
                        MovieContract.MovieEntry.COLUMN_NAME_MOVIENAME + " LIKE '%" + newText + "%'",//조건
                        null,
                        null,
                        null,
                        null
                );
                mAdapter.swap(mMovieList);
                return true;
            }
        });

        mMovieList = new ArrayList<Movie>();
        mMovieFacade = new MovieFacade(this);

        // 데이터
        mMovieList = mMovieFacade.getMovieList();
        // 어댑터
        mAdapter = new MovieRecyclerAdapter(this,mMovieList);

        mMemoRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // 삭제 버튼 클릭
    @Subscribe
    public void onItemDelClickEvent(final MovieRecyclerAdapter.ItemDelClickEvent event){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("삭제 알림")
                .setMessage("정말 삭제하시겠습니까?")
                .setPositiveButton("삭제합니다", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteMemo(event.id);
                        Toast.makeText(MainActivity.this, "삭제하였습니다", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this, "삭제하지 않습니다", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
    }

    //데이터 삭제
    private void deleteMemo(long id) {
        int deleted = mMovieFacade.delete(id);
        if (deleted != 0) {
            Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            mAdapter.swap(mMovieFacade.getMovieList());
        }else{
            Toast.makeText(getApplicationContext(), "삭제 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}