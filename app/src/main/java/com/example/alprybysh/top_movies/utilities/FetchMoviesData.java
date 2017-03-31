package com.example.alprybysh.top_movies.utilities;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alprybysh.top_movies.MoviesAdapter;
import com.example.alprybysh.top_movies.R;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by aprybysh on 3/31/17.
 */

public class FetchMoviesData extends AsyncTask<String, Void, String> {


    private ArrayList<String> mMoviesData;
    private ArrayList<String> mPostersPath;
    private ArrayList<String> mOverview;
    private ArrayList<String> mReleaseDate;
    private ArrayList<String> mRating;
    private MoviesAdapter adapter;
    private ProgressBar mLoadingIndicator;

    public FetchMoviesData(ProgressBar progressBar, MoviesAdapter mAdapter) {

        adapter = mAdapter;
        mLoadingIndicator = progressBar;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    /* Fetch movie's data*/
    @Override
    protected String doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }
        String moviesData = params[0];
        URL moviesRequestUrl = NetworkUtils.buildUrl(moviesData);

        try {

            String jsonMoviesResponse = NetworkUtils.getResponceFromHttpUrl(moviesRequestUrl);

            ParseJsonUtil.fetchMoviesData(jsonMoviesResponse);

            mMoviesData = ParseJsonUtil.getTitles();
            mPostersPath = ParseJsonUtil.getPosterPath();
            mRating = ParseJsonUtil.getRating();
            mOverview = ParseJsonUtil.getOverView();
            mReleaseDate = ParseJsonUtil.getReleaseDate();

            return jsonMoviesResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /*Setting moive's data into adapter*/
    @Override
    protected void onPostExecute(String s) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        adapter.setPostersPath(mPostersPath);
        adapter.setTitles(mMoviesData);
        adapter.setRating(mRating);
        adapter.setReleaseDate(mReleaseDate);
        adapter.setmOverview(mOverview);
        super.onPostExecute(s);
    }
}
