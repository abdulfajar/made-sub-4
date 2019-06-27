package com.fajarduke.katalogfilm.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseTvContract {

    public static final String AUTHORITY = "com.fajarduke.katalogfilm";
    public static final String SCHEME = "fajar";

    private DatabaseTvContract(){}
//
//    public static final class FavoriteColumns implements BaseColumns {
//
////        public static String TABLE_FAVORITE = "favorite";
////
////        public static String NAME = "name";
////        public static String POSTER = "poster";
////        public static String RELEASE_DATE = "date";
////        public static String DESCRIPTION = "description";
////
////        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
////                .authority(AUTHORITY)
////                .appendPath(TABLE_FAVORITE)
////                .build();
////
////    }

    public static final class TvColumns implements BaseColumns {

        public static String TABLE_TV = "tv";

        public static String NAME_TV = "name";
        public static String POSTER_TV = "poster";
        public static String RELEASE_DATE_TV = "date";
        public static String DESCRIPTION_TV = "description";

        public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme("testos")
                .authority(AUTHORITY)
                .appendPath(TABLE_TV)
                .build();

    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

}