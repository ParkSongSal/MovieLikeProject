package com.example.movielikeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movielikeproject.Adapter.MovieRecyclerAdapter;
import com.example.movielikeproject.DB.MovieFacade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieWriteActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener callbackMethod;

    private EditText movieName_edit, director_edit, actor_edit, genre_edit, review_edit, movieDate_edit;
    private long mMovieId = -1;

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date nowdate = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(nowdate);

    Intent intent;

    private MovieFacade mMovieFacade;
    private MovieRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_write);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("등록");

        movieName_edit = findViewById(R.id.movieNameEdit);
        director_edit = findViewById(R.id.directorEdit);
        genre_edit = findViewById(R.id.genreEdit);
        actor_edit = findViewById(R.id.actorEdit);
        review_edit = findViewById(R.id.reviewEdit);
        movieDate_edit = findViewById(R.id.movieDateEdit);
        mMovieFacade = new MovieFacade(this);

        initDateListener();
        movieDate_edit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog dialog = new DatePickerDialog(MovieWriteActivity.this, callbackMethod, 2021,12, 7);
                    dialog.show();

                }
            }
        });
    }

    public void initDateListener(){
        callbackMethod = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

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
                AlertDialog.Builder dialog = new AlertDialog.Builder(MovieWriteActivity.this);
                dialog.setTitle("종료 알림")

                        .setMessage("작성 중인 영화를 저장하시겠습니까?")

                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                save();

                                Toast.makeText(MovieWriteActivity.this, "저장하였습니다.", Toast.LENGTH_SHORT).show();
                            }

                        })

                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                cancel();
                                Toast.makeText(MovieWriteActivity.this, "저장하지 않았습니다.", Toast.LENGTH_SHORT).show();

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
        finish();
    }

    private void save(){

        String movieName = movieName_edit.getText().toString();
        String director = director_edit.getText().toString();
        String genre = genre_edit.getText().toString();
        String actor = actor_edit.getText().toString();
        String review = review_edit.getText().toString();
        String movieDate = movieDate_edit.getText().toString();

        long newRowId = mMovieFacade.insert(movieName,
                director,
                genre,
                actor,
                review,
                movieDate,
                movieDate);
        if(newRowId == -1){
            Toast.makeText(getApplicationContext(), "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}