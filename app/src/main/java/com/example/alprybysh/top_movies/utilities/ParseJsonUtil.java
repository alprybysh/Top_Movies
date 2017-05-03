package com.example.alprybysh.top_movies.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.alprybysh.top_movies.Movie;
import com.example.alprybysh.top_movies.data.MoviesContract;
import com.example.alprybysh.top_movies.data.SetMoviesDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aprybysh on 3/28/17.
 */

public class ParseJsonUtil {


    private JSONObject jsonObject;
    private ArrayList<String> titles;
    private ArrayList<String> postersPAth;
    private ArrayList<String> overView;
    private ArrayList<String> rating;
    private ArrayList<String> releaseDate;
    private ArrayList<Integer> mID;
    private ArrayList<String> reviews;
    private ArrayList<String> author;
    private ArrayList<String> trailers;
    private ArrayList<String> nameTrailers;
    private JSONArray array;
    private static final String BASE_PATH = "http://image.tmdb.org/t/p/";
    private static final String BASE_SIZE = "w500";

    private ArrayList<Movie> mMoviesArray;
    private SetMoviesDatabase setMoviesDatabase;


    public Movie mMovies;

    /*
    Create a JsonObject and retrieve a JsonAray of movie's data

    @param  moviesData the response from tmbd server in a string representation
     */
    public void fetchMoviesData(String moviesData) {

        try {
            jsonObject = new JSONObject(moviesData);
            array = jsonObject.getJSONArray("results");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public ArrayList<Integer> getID() {

        mID = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                mID.add(i, array.getJSONObject(i).getInt("id"));
                Log.v("Message", Integer.toString(array.getJSONObject(i).getInt("id")));


            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mID;
    }

    /*This method is used for getting an array list of titles of movies*/
    public ArrayList<String> getTitles() {


        titles = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                titles.add(i, array.getJSONObject(i).getString("original_title"));


            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        return titles;
    }

    /*This method is used for getting an array list of posters paths of movies*/
    public ArrayList<String> getPosterPath() {

        postersPAth = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                postersPAth.add(i, BASE_PATH + BASE_SIZE + array.getJSONObject(i).getString("poster_path"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return postersPAth;
    }

    /*This method is used for getting an array list of overviews of movies*/
    public ArrayList<String> getOverView() {

        overView = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                overView.add(i, array.getJSONObject(i).getString("overview"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return overView;
    }

    /*This method is used for getting an array list of ratings of movies*/
    public ArrayList<String> getRating() {

        rating = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                rating.add(i, array.getJSONObject(i).getString("vote_average") + "/10");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rating;
    }

    /*This method is used for getting an array list of releases dates of movies*/
    public ArrayList<String> getReleaseDate() {

        releaseDate = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {

                releaseDate.add(i, array.getJSONObject(i).getString("release_date").substring(0, 4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return releaseDate;
    }

    /*This method is used for getting an array list of  authors of reviews*/
    public ArrayList<String> getAuthor() {

        author = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                author.add(i, array.getJSONObject(i).getString("author"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return author;
    }


    /*This method is used for getting an array list  of reviews*/
    public ArrayList<String> getReviews() {

        reviews = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                reviews.add(i, array.getJSONObject(i).getString("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return reviews;
    }


    /*This method is used for getting an array list  of trailers path */
    public ArrayList<String> getTrailers() {

        trailers = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                trailers.add(i, array.getJSONObject(i).getString("key"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return trailers;
    }


    /*This method is used for getting an array list of names of trailers*/
    public ArrayList<String> getNameTrailers() {

        nameTrailers = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                nameTrailers.add(i, array.getJSONObject(i).getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nameTrailers;
    }


    public Movie getReviewsData() {

        getAuthor();
        getReviews();

        mMovies = new Movie();
        mMovies.setmReviews(getReviews());
        mMovies.setmAuthor(getAuthor());

        return mMovies;
    }


    public Movie getTrailersData() {
        getNameTrailers();
        getTrailers();

        mMovies = new Movie();
        mMovies.setmTrailers(getTrailers());
        mMovies.setmNameTrailers(getNameTrailers());

        return mMovies;
    }


    public ArrayList<Movie> movies() {
        getID();
        getTitles();
        getPosterPath();
        getOverView();
        getRating();
        getReleaseDate();

        mMoviesArray = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {

            mMovies = new Movie();
            mMovies.setmTitle(titles.get(i));
            mMovies.setmPath(postersPAth.get(i));
            mMovies.setmOverview(overView.get(i));
            mMovies.setmReleaseDate(releaseDate.get(i));
            mMovies.setmRating(rating.get(i));
            mMovies.setmID(mID.get(i));
            mMoviesArray.add(mMovies);

        }
        return mMoviesArray;
    }
}


