package com.example.alprybysh.top_movies.utilities;

import android.content.Intent;
import android.util.Log;

import com.example.alprybysh.top_movies.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aprybysh on 3/28/17.
 */

public class ParseJsonUtil {

    private static JSONObject jsonObject;
    private static ArrayList<String> titles;
    private static ArrayList<String> postersPAth;
    private static ArrayList<String> overView;
    private static ArrayList<String> rating;
    private static ArrayList<String> releaseDate;
    private static JSONArray array;
    private static final String BASE_PATH = "http://image.tmdb.org/t/p/w342";


    public static void fetchMoviesData(String moviesData) {

        try {
            jsonObject = new JSONObject(moviesData);
            array = jsonObject.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static ArrayList<Integer> getID(){
//        idMovie = new ArrayList<>();
//
//        try {
//            for (int i = 0; i < array.length(); i++) {
//                idMovie.add(i, Integer.parseInt(array.getJSONObject(i).getString("id")));
//                Log.v("1234", String.valueOf(idMovie.get(i)));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return idMovie;
//    }


    public static ArrayList<String> getTitles() {

        titles = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                titles.add(i, array.getJSONObject(i).getString("original_title"));
                //Log.v("5678", arr[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return titles;
    }

    public static ArrayList<String> getPosterPath() {

        postersPAth = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                postersPAth.add(i, BASE_PATH + array.getJSONObject(i).getString("poster_path"));
                // Log.v("1234", postersPAth.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return postersPAth;
    }

    public static ArrayList<String> getOverView() {

        overView = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                overView.add(i, array.getJSONObject(i).getString("overview"));
                // Log.v("1234", postersPAth.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return overView;
    }

    public static ArrayList<String> getRating() {

        rating = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                rating.add(i, array.getJSONObject(i).getString("vote_average"));
                // Log.v("1234", postersPAth.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rating;
    }


    public static ArrayList<String> getReleaseDate() {

        releaseDate = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                releaseDate.add(i, array.getJSONObject(i).getString("release_date"));
                // Log.v("1234", postersPAth.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return releaseDate;
    }


}


