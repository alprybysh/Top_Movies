package com.example.alprybysh.top_movies.utilities;

import android.net.Uri;

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



    private  static String key = "?api_key=109de7a407dc592e0bd0e399255c5a37";

    public static URL buildUrl (String url_popular){
        Uri builtUri = Uri.parse(url_popular+key)
                .buildUpon()
                .build();

        URL url = null;

        try {
            url =new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponceFromHttpUrl (URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }

}
