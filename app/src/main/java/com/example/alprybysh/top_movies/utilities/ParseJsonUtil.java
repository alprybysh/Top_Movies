package com.example.alprybysh.top_movies.utilities;

import org.json.JSONArray;
import org.json.JSONObject;
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
    private static final String BASE_PATH = "http://image.tmdb.org/t/p/";
    private static final String BASE_SIZE = "w500";

    /*
    Create a JsonObject and retrieve a JsonAray of movie's data

    @param  moviesData the response from tmbd server in a string representation
     */
    public static void fetchMoviesData(String moviesData) {

        try {
            jsonObject = new JSONObject(moviesData);
            array = jsonObject.getJSONArray("results");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*This method is used for getting an array list of titles of movies*/
    public static ArrayList<String> getTitles() {

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
    public static ArrayList<String> getPosterPath() {

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
    public static ArrayList<String> getOverView() {

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
    public static ArrayList<String> getRating() {

        rating = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                rating.add(i, array.getJSONObject(i).getString("vote_average"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rating;
    }
    /*This method is used for getting an array list of releases dates of movies*/
    public static ArrayList<String> getReleaseDate() {

        releaseDate = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                releaseDate.add(i, array.getJSONObject(i).getString("release_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return releaseDate;
    }
}


