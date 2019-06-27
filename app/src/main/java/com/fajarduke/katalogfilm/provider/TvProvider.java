package com.fajarduke.katalogfilm.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.fajarduke.katalogfilm.db.TvHelper;

import static com.fajarduke.katalogfilm.db.DatabaseMovieContract.AUTHORITY;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.CONTENT_URI_TV;
import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.TABLE_TV;

public class TvProvider extends ContentProvider {


    private static final int FAVORITE_TV = 1;
    private static final int FAVORITE__TV_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(AUTHORITY, TABLE_TV, FAVORITE_TV);
        sUriMatcher.addURI(AUTHORITY,
                TABLE_TV + "/#",
                FAVORITE__TV_ID);
    }

    private TvHelper favoriteHelper;

    @Override
    public boolean onCreate() {
        favoriteHelper = new TvHelper(getContext());
        favoriteHelper.open();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_TV:
                cursor = favoriteHelper.queryProviderTv();
                break;
            case FAVORITE__TV_ID:
                cursor = favoriteHelper.queryByIdProviderTv(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {

        long added;

        switch (sUriMatcher.match(uri)) {
            case FAVORITE_TV:
                added = favoriteHelper.insertProviderTv(contentValues);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI_TV + "/" + added);
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updated;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE__TV_ID:
                updated = favoriteHelper.updateProviderTv(uri.getLastPathSegment(), contentValues);
                break;
            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE__TV_ID:
                deleted = favoriteHelper.deleteProviderTv(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }
}
