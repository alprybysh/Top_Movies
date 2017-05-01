package com.example.alprybysh.top_movies.utilities;

import android.net.Uri;
import android.os.Build;
import android.support.compat.BuildConfig;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by aprybysh on 3/27/17.
 */

public class NetworkUtils {


    /**
     * Builds the URL used to talk to the  server
     * @param url_popular The endpoint that will be queried for.
     * @return The URL to use to query the server.
     */



    private static final String KEY = com.example.alprybysh.top_movies.BuildConfig.API_KEY;

    private static final String BASE_URL = "https://www.youtube.com/watch?v=";


    public static String buildUrlReviews(String mBaseUrl, String end, int mMovieId) {

        StringBuilder builder = new StringBuilder();
        builder.append(mBaseUrl);
        builder.append(Integer.toString(mMovieId));
        builder.append(end);
        return builder.toString();
    }


    public static Uri buildTrailerUri(String path){

        Uri builtUri = Uri.parse(BASE_URL + path)
                .buildUpon()
                .build();


        return builtUri;
    }

    public static URL buildUrl(String url_popular) {
        Uri builtUri = Uri.parse(url_popular + KEY)
                .buildUpon()
                .build();

        Log.v("Key", builtUri.toString());

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Builds the URL used to talk to the server
     *
     * @param url The url

     * @return The Url to use to query the server.
     */

    public static String getResponceFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
