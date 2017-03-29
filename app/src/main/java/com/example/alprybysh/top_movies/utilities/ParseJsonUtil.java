package com.example.alprybysh.top_movies.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by aprybysh on 3/28/17.
 */

public class ParseJsonUtil {

    private static JSONObject jsonObject;
    private static  JSONArray array;
    private static final String  BASE_PATH = "http://image.tmdb.org/t/p/w342";


    public static JSONObject createJson(String moviesData) {

        try {
            jsonObject = new JSONObject(moviesData);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return jsonObject;
    }


    public static String[] fetchMoviesData(String jsonDate) throws JSONException {

        createJson(jsonDate);

        JSONArray array = jsonObject.getJSONArray("results");

        String[] arr = new String[array.length()];


        for (int i = 0; i < array.length(); i++) {

            arr[i] = array.getJSONObject(i).getString("original_title");
            Log.v("5678", arr[i]);

        }
        fetchPosterPath();
        return arr;
    }

    public static String[] fetchPosterPath() throws JSONException {

        array = jsonObject.getJSONArray("results");
        String[] arr = new String[array.length()];


        for (int i = 0; i < array.length(); i++) {

            arr[i] = BASE_PATH + array.getJSONObject(i).getString("poster_path");
            Log.v("1234", arr[i]);

        }
        return arr;

    }




}
