package com.example.movielikeproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielikeproject.Model.Movie;
import com.example.movielikeproject.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<Movie> mData;
    private final Context mContext;

    public void swap(List<Movie> newMovieList) {
        mData = newMovieList;
        notifyDataSetChanged();
    }

    public MovieRecyclerAdapter(Context context, List<Movie> movieList) {

        mData = movieList;
        mContext = context;
    }

    //Event Bus 클래스
    public static class ItemClickEvent {
        public ItemClickEvent(int position, long id) {
            this.position = position;
            this.id = id;
        }
        public int position;
        public long id;

    }

    //Event Bus 클래스
    public static class ItemDelClickEvent {
        public ItemDelClickEvent(int position, long id) {
            this.position = position;
            this.id = id;
        }
        public int position;
        public long id;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //뷰를 새로 만들 때
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(convertView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // 데이터
        final Movie movie = mData.get(position);


        // 화면에 뿌리기
        holder.movieNameTextView.setText(movie.getMovieName());
        holder.directorTextView.setText(movie.getDirector() + " /");
        holder.actorTextView.setText(movie.getActor());
        holder.genreTextView.setText(movie.getGenre());
        holder.reviewTextView.setText(movie.getReview());
        holder.dateTextView.setText(movie.getMovieDate());

        float mRating = Float.parseFloat(movie.getRating());
        holder.ratingBar.setRating(mRating);

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ItemDelClickEvent(position, movie.getId()));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               EventBus.getDefault().post(new ItemClickEvent(position, movie.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieNameTextView;
        TextView directorTextView;
        TextView actorTextView;
        TextView genreTextView;

        TextView dateTextView;
        TextView reviewTextView;
        RatingBar ratingBar;

        ImageButton delBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            // 레이아웃 들고 오기
            TextView movieNameTextView = (TextView) itemView.findViewById(R.id.movieNameTxt);
            TextView directorTextView = (TextView) itemView.findViewById(R.id.directorTxt);
            TextView actorTextView = (TextView) itemView.findViewById(R.id.actorTxt);
            TextView genreTextView = (TextView) itemView.findViewById(R.id.genreTxt);
            RatingBar ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            movieNameTextView.setSelected(true);// 텍스트가 물흐르게 하는효과
            TextView contentTextView = (TextView) itemView.findViewById(R.id.reviewTxt);
            contentTextView.setEllipsize(TextUtils.TruncateAt.END);

            TextView dateTextView = (TextView) itemView.findViewById(R.id.datetxt);

            ImageButton delBtnView = (ImageButton) itemView.findViewById(R.id.delBtn);


            this.movieNameTextView = movieNameTextView;
            this.directorTextView = directorTextView;
            this.actorTextView = actorTextView;
            this.genreTextView = genreTextView;
            this.ratingBar = ratingBar;
            this.reviewTextView = contentTextView;
            this.dateTextView = dateTextView;
            this.delBtn = delBtnView;

        }

    }

}



