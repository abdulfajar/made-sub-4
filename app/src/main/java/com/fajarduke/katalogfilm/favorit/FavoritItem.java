package com.fajarduke.katalogfilm.favorit;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.fajarduke.katalogfilm.db.DatabaseMovieContract;

import static android.provider.BaseColumns._ID;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.getColumnInt;
import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.getColumnString;

public class FavoritItem implements Parcelable {
    private int id;
    private String img;
    private String title;
    private String date;
    private String desc;

    public FavoritItem() {
    }

    public FavoritItem(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseMovieContract.FavoriteColumns.NAME);
        this.img = getColumnString(cursor, DatabaseMovieContract.FavoriteColumns.POSTER);
        this.date = getColumnString(cursor, DatabaseMovieContract.FavoriteColumns.RELEASE_DATE);
        this.desc = getColumnString(cursor, DatabaseMovieContract.FavoriteColumns.DESCRIPTION);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected FavoritItem(Parcel in) {
        id = in.readInt();
        img = in.readString();
        title = in.readString();
        date = in.readString();
        desc = in.readString();
    }

    public static final Parcelable.Creator<FavoritItem> CREATOR = new Parcelable.Creator<FavoritItem>() {
        @Override
        public FavoritItem createFromParcel(Parcel source) {
            return new FavoritItem(source);
        }

        @Override
        public FavoritItem[] newArray(int size) {
            return new FavoritItem[size];
        }
    };

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(img);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(desc);
    }
}
