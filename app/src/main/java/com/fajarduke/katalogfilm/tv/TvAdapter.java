package com.fajarduke.katalogfilm.tv;

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

public class TvAdapter extends RecyclerView.Adapter <TvAdapter.TvViewHolder> {
    private ArrayList<TvItem> mData = new ArrayList<>();
    public void setData(ArrayList<TvItem> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new TvViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvViewHolder tvViewHolder, int i) {
        tvViewHolder.bind(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;
        TextView textViewTgl;
        ImageView imgViewPhoto;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.txt_name);
            textViewTgl = itemView.findViewById(R.id.txt_tgl);
            imgViewPhoto = itemView.findViewById(R.id.img_photo);
        }


        void bind(TvItem movie) {
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
