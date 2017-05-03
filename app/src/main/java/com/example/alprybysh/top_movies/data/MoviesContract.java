package com.example.alprybysh.top_movies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by aprybysh on 4/20/17.
 */

public class MoviesContract {

        /*
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website. A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * Play Store.
     */

    public static final String CONTENT_AUTHORITY = "com.example.alprybysh.top_movies.data";


    /*
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider for Sunshine.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "movies" directory

    public static final String PATH_MOVIE = "movies";


    public static final class MoviesEntry implements BaseColumns {


        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();


        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_TITLE = "movieTitle";
        public static final String COLUMN_IDENTIFIER = "identifier";
        public static final String COLUMN_POSTER = "moviePoster";
        public static final String COLUMN_OVERVIEW = "movieOverview";
        public static final String COLUMN_USER_RATING = "movieRating";
        public static final String COLUMN_RELEASE_DATE = "movieRelease";
        public static final String COLUMN_TRAILER = "movieTrailer";
        public static final String COLUMN_REVIEWS = "movieReviews";
    }
}
