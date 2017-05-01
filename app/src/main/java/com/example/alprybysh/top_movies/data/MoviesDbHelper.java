package com.example.alprybysh.top_movies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aprybysh on 4/20/17.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    //The database name
    private static final String DATABASE_NAME = "movies.db";

    //The database version
    private static final int DATABASE_VERSION = 14;

    public static final String LOG_TAG = MoviesDbHelper.class.getSimpleName();

    //Constructor


    public MoviesDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // @param db The database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a table that keeps movies data

        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MoviesContract.MoviesEntry.TABLE_NAME + " (" +
                MoviesContract.MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.MoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MoviesContract.MoviesEntry.COLUMN_IDENTIFIER + " INTEGER NOT NUll, "+
                MoviesContract.MoviesEntry.COLUMN_POSTER + " TEXT NOT NULL, " +
                MoviesContract.MoviesEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MoviesContract.MoviesEntry.COLUMN_TRAILER + " TEXT, " +
                MoviesContract.MoviesEntry.COLUMN_USER_RATING + " TEXT NOT NULL, " +
                MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                MoviesContract.MoviesEntry.COLUMN_REVIEWS + " TEXT " + ");";

        Log.v(LOG_TAG, "Creating DB" + SQL_CREATE_MOVIE_TABLE);

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }
    // @param db Database that is being upgraded
    // @param oldVersion     The old database version
    // @param newVersion     The new database version

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME);
        onCreate(db);
    }
}
