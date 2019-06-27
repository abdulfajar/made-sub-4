package com.fajarduke.katalogfilm.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fajarduke.katalogfilm.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieItem> mData = new ArrayList<>();
    public void setData(ArrayList<MovieItem> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mData.get(i));
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;
        TextView textViewTgl;
        ImageView imgViewPhoto;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.txt_name);
            textViewTgl = itemView.findViewById(R.id.txt_tgl);
            imgViewPhoto = itemView.findViewById(R.id.img_photo);
        }

        void bind(MovieItem movie) {
            textViewNama.setText(movie.getTitle());
            textViewTgl.setText(movie.getDate());
            String gbr = "https://image.tmdb.org/t/p/w185" + movie.getImg();
            Glide.with(itemView.getContext())
                    .load(gbr)
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgViewPhoto);
        }
    }
}
