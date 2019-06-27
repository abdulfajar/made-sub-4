package com.fajarduke.katalogfilm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.DESCRIPTION;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.NAME;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.POSTER;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.RELEASE_DATE;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.TABLE_FAVORITE;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.DESCRIPTION_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.NAME_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.POSTER_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.RELEASE_DATE_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.TABLE_TV;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_favorite";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAVORITE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_FAVORITE,
            _ID,
            NAME,
            POSTER,
            RELEASE_DATE,
            DESCRIPTION
    );

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_TV,
            _ID,
            NAME_TV,
            POSTER_TV,
            RELEASE_DATE_TV,
            DESCRIPTION_TV
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAVORITE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FAVORITE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TV);
        onCreate(db);
    }
}
