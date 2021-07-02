package com.june.networktest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            date = itemView.findViewById(R.id.movie_openDt);
            count = itemView.findViewById(R.id.movie_audiCnt);
        }

        public void setItem(Movie item) {
            title.setText(item.movieNm);
            date.setText(item.openDt);
            count.setText(item.audiCnt + " 명");
        }
    }

    ArrayList<Movie> movies = new ArrayList<Movie>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 실제로 movie_item 레이아웃을 변경하는 메소드
        Movie movie = movies.get(position);
        holder.setItem(movie);
    }

    @Override
    public int getItemCount() {
        // 데이터의 개수
        return (movies != null? movies.size() : 0);
    }

    // 데이터 세팅을 위한 메소드
    public void setItems(ArrayList<Movie> items) {
        this.movies = items;
    }
}
