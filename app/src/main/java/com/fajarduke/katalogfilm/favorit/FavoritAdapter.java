package com.fajarduke.katalogfilm.favorit;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fajarduke.katalogfilm.DetailActivity;
import com.fajarduke.katalogfilm.R;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {

    private Context context;
    private Cursor listFavorite;

    public FavoritAdapter(Context context){
        this.context = context;
    }

    public void setListFavorit(Cursor listFavorite) {
        this.listFavorite = listFavorite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FavoritItem favorite = getItem(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185"+favorite.getImg())
                .into(holder.gmb);
        holder.judul.setText(favorite.getTitle());
        holder.tgl.setText(favorite.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra(DetailActivity.EXTRA_DATA, favorite);
                intent.putExtra(DetailActivity.EXTRA_NAME, 3);
                context.startActivity(intent);
            }
        });
        Log.e("DATE", ""+favorite.getDate());
    }

    @Override
    public int getItemCount() {
        if (listFavorite == null) return 0;
        return listFavorite.getCount();
    }

    private FavoritItem getItem(int position){
        if (!listFavorite.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new FavoritItem(listFavorite);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView gmb;
        TextView judul, tgl;
        RecyclerView rv_list;

        ViewHolder(View itemView) {
            super(itemView);

            gmb = itemView.findViewById(R.id.img_photo);
            judul = itemView.findViewById(R.id.txt_name);
            tgl = itemView.findViewById(R.id.txt_tgl);
            rv_list = itemView.findViewById(R.id.rv_fav_movie);
        }
    }

}
