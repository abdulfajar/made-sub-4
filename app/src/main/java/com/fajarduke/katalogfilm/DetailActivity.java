package com.fajarduke.katalogfilm;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fajarduke.katalogfilm.db.DatabaseMovieContract;
import com.fajarduke.katalogfilm.db.MovieHelper;
import com.fajarduke.katalogfilm.favorit.FavoritItem;
import com.fajarduke.katalogfilm.movie.MovieItem;
import com.fajarduke.katalogfilm.tv.TvItem;

import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.CONTENT_URI;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.DESCRIPTION;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.NAME;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.POSTER;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.RELEASE_DATE;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.CONTENT_URI_TV;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_POSITION = "extra_position";
    private String nama,date,des,gbr,bar;
    private Boolean isFavorite = false;
    private FloatingActionButton fav;
    private long position,id;
    private Integer nam, tes;
    private FavoritItem item;
    private MovieHelper noteHelper;

    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_UPDATE = 200;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;

    TextView name;
    TextView tgl;
    TextView desc;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = findViewById(R.id.tv_nama);
        tgl = findViewById(R.id.tv_tgl);
        desc = findViewById(R.id.tv_desc);
        img = findViewById(R.id.img);
        fav = findViewById(R.id.fav_btn);
        fav.setOnClickListener(this);

        nam = getIntent().getIntExtra(EXTRA_NAME,0);
        if (nam == 1)
        {   movie();
        }
        if (nam == 2)
        {
            tv();
        }
        if (nam == 3)
        {
            fav_m();
        }

        if (nam == 4)
        {
            fav_t();
        }

        name.setText(nama);
        tgl.setText(date);
        desc.setText(des);
        Glide.with(this)
            .load(gbr).into(img);
        setFavorite();

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.fav_btn) {

            if (isFavorite)
            {
                getContentResolver().delete(
                        Uri.parse(CONTENT_URI + "/" + id),
                        null,
                        null
                );
                isFavorite = false;
            }
            else
            {
                ContentValues values = new ContentValues();
                values.put(NAME, nama);
                values.put(RELEASE_DATE, date);
                values.put(DESCRIPTION, des);
                values.put(POSTER, bar);
                if (tes == 1) { getContentResolver().insert(CONTENT_URI, values); }
                else {getContentResolver().insert(CONTENT_URI_TV, values);}
                Toast.makeText(getApplicationContext(),"Berhasil menambahkan ke playlist favorite" , Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }

            setFavorite();
        }
    }


    public boolean setFavorite(){
        cekData();
        if (isFavorite) {fav.setImageResource(R.drawable.ic_added_to_favorites);}
        else {fav.setImageResource(R.drawable.ic_add_to_favorites);}
        return isFavorite;
    }

    private void movie()
    {
        MovieItem item = getIntent().getParcelableExtra(EXTRA_DATA);
        nama = item.getTitle();
        date = item.getDate();
        des = item.getDesc();
        bar = item.getImg();
        tes = 1;
        gbr = "https://image.tmdb.org/t/p/w185" + bar;
    }

    private void tv()
    {
        TvItem item = getIntent().getParcelableExtra(EXTRA_DATA);
        nama = item.getTitle();
        date = item.getDate();
        des = item.getDesc();
        bar = item.getImg();
        tes = 2;
        gbr = "https://image.tmdb.org/t/p/w185" + bar;
    }

    private void fav_m()
    {
        FavoritItem item = getIntent().getParcelableExtra(EXTRA_DATA);
        nama = item.getTitle();
        date = item.getDate();
        des = item.getDesc();
        bar = item.getImg();
        gbr = "https://image.tmdb.org/t/p/w185" + bar;
    }

    private void fav_t()
    {
        FavoritItem item = getIntent().getParcelableExtra(EXTRA_DATA);
        nama = item.getTitle();
        date = item.getDate();
        des = item.getDesc();
        bar = item.getImg();
        gbr = "https://image.tmdb.org/t/p/w185" + bar;
    }

    private boolean cekData()
    {
        Uri uri = Uri.parse(CONTENT_URI+"");
        Cursor cursor = getContentResolver().query(uri, null,null,null,null);
        String getTitle;
        if (cursor.moveToFirst())
        {
            do {
                id = cursor.getLong(0);
                getTitle = cursor.getString(1);
                if (getTitle.equals(nama))
                {
                    isFavorite = true;
                }
            }while (cursor.moveToNext());
        }
        return isFavorite;
    }
}
