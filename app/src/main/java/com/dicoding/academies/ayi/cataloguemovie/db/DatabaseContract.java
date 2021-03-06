package com.dicoding.academies.ayi.cataloguemovie.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ayi on 03/01/18.
 */

public class DatabaseContract {
    public static String TABLE_MOVIE = "favorite";
    public static final class FavoriteColumns implements BaseColumns {
        //Note title
      public static String TITLE = "title";
        //Note description
      public static String DESCRIPTION = "description";
        //Note date
      public static String RELEASE_DATE = "date";
      public static String POPULARITY = "popularity";
      public static String BANNER = "banner";
      public static String POSTER = "poster";
    }

    public static final String AUTHORITY = "com.dicoding.academies.ayi.cataloguemovie";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build();
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}
