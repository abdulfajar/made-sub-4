package com.fajarduke.katalogfilm.tv;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvItem implements Parcelable {
    private String img;
    private String title;
    private String date;
    private String desc;

    public TvItem(JSONObject object) {
        try {
            String name = object.getString("name");
            String photo = object.getString("poster_path");
            String tgl = object.getString("first_air_date");
            String overview = object.getString("overview");
            this.title = name;
            this.img = photo;
            this.date = tgl;
            this.desc = overview;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected TvItem(Parcel in) {
        img = in.readString();
        title = in.readString();
        date = in.readString();
        desc = in.readString();
    }

    public static final Creator<TvItem> CREATOR = new Creator<TvItem>() {
        @Override
        public TvItem createFromParcel(Parcel in) {
            return new TvItem(in);
        }

        @Override
        public TvItem[] newArray(int size) {
            return new TvItem[size];
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
        dest.writeString(img);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(desc);
    }
}
