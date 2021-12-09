package com.example.movielikeproject.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public MovieRecyclerAdapter(Context context, List<Movie> movieList) {

        mData = movieList;
        mContext = context;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 데이터
        final Movie movie = mData.get(position);


        // 화면에 뿌리기
        holder.titleTextView.setText(movie.getTitle());
        holder.contentTextView.setText(movie.getContents());
        holder.dateTextView.setText(movie.getMovieDate());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView dateTextView;
        TextView contentTextView;
        ImageView imageView;
        //ImageButton delBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            // 레이아웃 들고 오기
            TextView titleTextView = (TextView) itemView.findViewById(R.id.titletxt);
            titleTextView.setSelected(true);// 텍스트가 물흐르게 하는효과
            TextView contentTextView = (TextView) itemView.findViewById(R.id.contentTxt);
            contentTextView.setEllipsize(TextUtils.TruncateAt.END);

            TextView dateTextView = (TextView) itemView.findViewById(R.id.datetxt);

            ImageView imgView = (ImageView) itemView.findViewById(R.id.imgView);
            //ImageButton delBtn = (ImageButton) itemView.findViewById(R.id.delBtn);


            this.titleTextView = titleTextView;
            this.contentTextView = contentTextView;
            this.dateTextView = dateTextView;
            this.imageView = imgView;
            //this.delBtn = delBtn;


        }

    }

}



