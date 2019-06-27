package com.fajarduke.katalogfilm.movie;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItem implements Parcelable{
    private String img;
    private String title;
    private String date;
    private String desc;


    protected MovieItem(Parcel in) {
        img = in.readString();
        title = in.readString();
        date = in.readString();
        desc = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
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

    public MovieItem(JSONObject object) {
        try {
            String name = object.getString("title");
            String photo = object.getString("poster_path");
            String tgl = object.getString("release_date");
            String overview = object.getString("overview");
            this.title = name;
            this.img = photo;
            this.date = tgl;
            this.desc = overview;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(desc);
    }
}
