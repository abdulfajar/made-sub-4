package com.fajarduke.katalogfilm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fajarduke.katalogfilm.favorit.FavoritItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.DESCRIPTION;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.NAME;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.POSTER;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.RELEASE_DATE;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.FavoriteColumns.TABLE_FAVORITE;

public class MovieHelper {

    private static String DATABASE_TABLE = TABLE_FAVORITE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public ArrayList<FavoritItem> getAllMovie() {
        ArrayList<FavoritItem> arrayList = new ArrayList<FavoritItem>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        FavoritItem favorite;
        if (cursor.getCount() > 0) {
            do {

                favorite = new FavoritItem();
                favorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favorite.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                favorite.setImg(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                favorite.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                favorite.setDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
//                favorite.setTes(cursor.getInt(cursor.getColumnIndexOrThrow(TES)));

                arrayList.add(favorite);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                , new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }

    public long insertProvider(ContentValues values){
//        Log.e("CONTENT VALUES :", ""+values.toString());
        return database.insert(DATABASE_TABLE,null, values);
    }

    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}
