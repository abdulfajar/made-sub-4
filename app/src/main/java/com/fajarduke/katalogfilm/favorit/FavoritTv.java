package com.fajarduke.katalogfilm.favorit;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.fajarduke.katalogfilm.db.DatabaseMovieContract;
import com.fajarduke.katalogfilm.db.DatabaseTvContract;

import static android.provider.BaseColumns._ID;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.getColumnInt;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.getColumnString;

public class FavoritTv implements Parcelable {
    private int id;
    private String img;
    private String title;
    private String date;
    private String desc;

    public FavoritTv() {
    }

    public FavoritTv(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseTvContract.TvColumns.NAME_TV);
        this.img = getColumnString(cursor, DatabaseTvContract.TvColumns.POSTER_TV);
        this.date = getColumnString(cursor, DatabaseTvContract.TvColumns.RELEASE_DATE_TV);
        this.desc = getColumnString(cursor, DatabaseTvContract.TvColumns.DESCRIPTION_TV);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected FavoritTv(Parcel in) {
        id = in.readInt();
        img = in.readString();
        title = in.readString();
        date = in.readString();
        desc = in.readString();
    }

    public static final Parcelable.Creator<FavoritTv> CREATOR = new Parcelable.Creator<FavoritTv>() {
        @Override
        public FavoritTv createFromParcel(Parcel source) {
            return new FavoritTv(source);
        }

        @Override
        public FavoritTv[] newArray(int size) {
            return new FavoritTv[size];
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

