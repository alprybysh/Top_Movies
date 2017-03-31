package com.example.alprybysh.top_movies;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aprybysh on 3/29/17.
 */

public class Movie implements Parcelable{


    private static String mTitle;
    private static String mRating;
    private static String mPath;
    private static String mOverview;
    private static String mReleaseDate;

    public Movie() {

    }

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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mTitle);
        dest.writeString(mRating);
        dest.writeString(mPath);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);

    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){

        public Movie createFromParcel(Parcel in){
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    Movie(Parcel in) {

        mTitle =in.readString();
        mRating =in.readString();
        mPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }
}
