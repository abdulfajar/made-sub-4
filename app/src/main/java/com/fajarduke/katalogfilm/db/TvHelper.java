package com.fajarduke.katalogfilm.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fajarduke.katalogfilm.favorit.FavoritTv;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.DESCRIPTION_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.NAME_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.POSTER_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.RELEASE_DATE_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.TABLE_TV;

public class TvHelper {

    private static String DATABASE_TABLE = TABLE_TV;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public TvHelper(Context context){
        this.context = context;
    }

    public TvHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public ArrayList<FavoritTv> query() {
        ArrayList<FavoritTv> arrayList = new ArrayList<FavoritTv>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        FavoritTv favorite;
        if (cursor.getCount() > 0) {
            do {

                favorite = new FavoritTv();
                favorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favorite.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NAME_TV)));
                favorite.setImg(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_TV)));
                favorite.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_TV)));
                favorite.setDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE_TV)));
//                favorite.setTes(cursor.getInt(cursor.getColumnIndexOrThrow(TES_TV)));
                arrayList.add(favorite);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryByIdProviderTv(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                , new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public Cursor queryProviderTv(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }

    public long insertProviderTv(ContentValues values){
//        Log.e("CONTENT VALUES :", ""+values.toString());
        return database.insert(DATABASE_TABLE,null, values);
    }

    public int updateProviderTv(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }

    public int deleteProviderTv(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}