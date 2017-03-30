package com.example.alprybysh.top_movies;

import android.net.Uri;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aprybysh on 3/29/17.
 */

public class Movie {


    private static String mTitle;
    private static String mRating;
    private static String mPath;
    private static String mOverview;
    private static String mReleaseDate;

    public static String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public static String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public static String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public static String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public static String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
