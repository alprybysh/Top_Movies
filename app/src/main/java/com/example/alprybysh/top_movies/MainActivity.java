package com.example.alprybysh.top_movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alprybysh.top_movies.utilities.NetworkUtils;
import com.example.alprybysh.top_movies.utilities.ParseJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesOnClickListenerHandler {

    private MoviesAdapter adapter;
    private static final String BASE_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular";
    private static final String BASE_URL_RATED = "http://api.themoviedb.org/3/movie/top_rated";

    private RecyclerView mRecyclerView;

    private ArrayList<String> mMoviesData;
    private ArrayList<String> mPostersPath;
    private ArrayList<String> mOverview;
    private ArrayList<String> mReleaseDate;
    private ArrayList<String> mRating;

    private ProgressBar mLoadingIndicator;

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.display_list_of_movies);


        /*Setting the GridLayoutManager as a manager for the RecyclerView*/



        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns());


        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesAdapter(this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(adapter);

        /*
         * The ProgressBar that will indicate to the user that we are loading data. It will be
         * hidden when no data is loading.*/
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);


        /* Once all of our views are setup, we can load the weather data. */
        loadMoviesData(BASE_URL_POPULAR);

    }

    /*This method run the background task*/
    public void loadMoviesData(String urlData) {

        new FetchMoviesData().execute(urlData);

    }
    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param movieClicked The movie that was clicked
     */
    @Override
    public void onItemClick(String movieClicked) {

        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_TEXT, movieClicked);
        startActivity(intent);
    }


    public class FetchMoviesData extends AsyncTask<String, Void, String> {

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
    /*This method is used for creating the setting menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    /*This method is used for setting the data according a user's choise*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.show_the_most_popular) {
            adapter.setTitles(null);
            adapter.setPostersPath(null);
            adapter.setRating(null);
            adapter.setReleaseDate(null);
            adapter.setmOverview(null);
            loadMoviesData(BASE_URL_POPULAR);
        }

        if (id == R.id.show_the_most_rated) {
            adapter.setTitles(null);
            adapter.setPostersPath(null);
            adapter.setRating(null);
            adapter.setReleaseDate(null);
            adapter.setmOverview(null);
            loadMoviesData(BASE_URL_RATED);
        }
        return super.onOptionsItemSelected(item);
    }

}
