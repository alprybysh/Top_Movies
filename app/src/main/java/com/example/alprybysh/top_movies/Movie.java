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


    private  String mTitle;
    private  String mRating;
    private  String mPath;
    private  String mOverview;
    private  String mReleaseDate;
    private ArrayList<String> mReviews;
    private ArrayList<String> mAuthor;
    private ArrayList<String> mTrailers;
    private ArrayList<String> mNameTrailers;
    private  int mID;


    private ArrayList <Movie> movieArrayList;


    public Movie() {

    }



    public  String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public  String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public  String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public  String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public  String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }


    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public ArrayList<String> getmReviews() {
        return mReviews;
    }

    public void setmReviews(ArrayList<String> mReviews) {
        this.mReviews = mReviews;
    }


    public ArrayList<String> getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(ArrayList<String> mAuthor) {
        this.mAuthor = mAuthor;
    }


    public ArrayList<String> getmTrailers() {
        return mTrailers;
    }

    public void setmTrailers(ArrayList<String> mTrailers) {
        this.mTrailers = mTrailers;
    }



    public ArrayList<String> getmNameTrailers() {
        return mNameTrailers;
    }

    public void setmNameTrailers(ArrayList<String> mNameTrailers) {
        this.mNameTrailers = mNameTrailers;
    }

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
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
        dest.writeInt(mID);
        dest.writeSerializable(mReviews);
        dest.writeSerializable(mAuthor);
        dest.writeSerializable(mNameTrailers);
        dest.writeSerializable(mTrailers);
        dest.writeSerializable(movieArrayList);


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
        mID = in.readInt();
        mReviews = (ArrayList<String>) in.readSerializable();
        mAuthor = (ArrayList<String>) in.readSerializable();
        mNameTrailers = (ArrayList<String>) in.readSerializable();
        mTrailers = (ArrayList<String>) in.readSerializable();
        movieArrayList = (ArrayList<Movie>) in.readSerializable();

    }
}
