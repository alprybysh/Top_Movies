package com.example.alprybysh.top_movies.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alprybysh.top_movies.Movie;
import com.example.alprybysh.top_movies.MoviesAdapter;

import java.net.URL;
import java.util.ArrayList;


/**
 * Created by aprybysh on 3/31/17.
 */

public class FetchMoviesData extends AsyncTask<String, Void, ArrayList<Movie>> {

    private MoviesAdapter adapter;
    private ProgressBar mLoadingIndicator;
    private ArrayList<Movie> mMovies;
    private ParseJsonUtil mParseJsonUtil;
    private String mUrl;
    Context mContext;



    public FetchMoviesData(ProgressBar progressBar, MoviesAdapter mAdapter, Context context) {

        adapter = mAdapter;
        mLoadingIndicator = progressBar;
        mContext = context;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    /* Fetch movie's data*/
    @Override
    protected ArrayList<Movie> doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        mMovies =new ArrayList<>();
        String moviesData = params[0];

        URL moviesRequestUrl = NetworkUtils.buildUrl(moviesData);

        try {

            mParseJsonUtil = new ParseJsonUtil();
            String jsonMoviesResponse = NetworkUtils.getResponceFromHttpUrl(moviesRequestUrl);
            mParseJsonUtil.fetchMoviesData(jsonMoviesResponse);
            mMovies = mParseJsonUtil.movies();

          //  return mMovies;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
     return mMovies;
    }

    /*Setting moive's data into adapter*/
    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        adapter.setMoviesData(movies);
        super.onPostExecute(movies);
    }
}
