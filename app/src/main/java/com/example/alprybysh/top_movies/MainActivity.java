package com.example.alprybysh.top_movies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alprybysh.top_movies.utilities.NetworkUtils;
import com.example.alprybysh.top_movies.utilities.ParseJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesOnClickListenerHandler {

    private MoviesAdapter adapter;
    private Toast mToast;
    private static final String BASE_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular";
    private static final String BASE_URL_RATED = "http://api.themoviedb.org/3/movie/top_rated";

    private RecyclerView mRecyclerView;


    private ArrayList<String> mMoviesData;
    private ArrayList<String> mPostersPath;
    private  ArrayList<String> mOverview;
    private  ArrayList<String> mReleaseDate;
    private  ArrayList<String>mRating;

    private ProgressBar mLoadingIndicator;




    private Movie mMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.display_list_of_movies);
        int numberOfColumns = 3;

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesAdapter(this);

        mRecyclerView.setAdapter(adapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMoviesData(BASE_URL_POPULAR);


    }

    public void loadMoviesData(String urlData) {

        new FetchMoviesData().execute(urlData);


    }

    @Override
    public void onItemClick(String s) {

        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(intent);


//        if (mToast != null) {
//            mToast.cancel();
//        }
//
//        String toastMessage = s + " clicked.";
//        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//
//        mToast.show();

    }

    public class FetchMoviesData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

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

        @Override
        protected void onPostExecute(String s) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            adapter.setPostersPath(mPostersPath);
            adapter.setMoviesData(mMoviesData);
            adapter.setRating(mRating);
            adapter.setReleaseDate(mReleaseDate);
            adapter.setmOverview(mOverview);
            super.onPostExecute(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.show_the_most_popular) {

            adapter.setMoviesData(null);
            adapter.setPostersPath(null);
            adapter.setRating(null);
            adapter.setReleaseDate(null);
            adapter.setmOverview(null);
            loadMoviesData(BASE_URL_POPULAR);

        }

        if (id == R.id.show_the_most_rated) {

            adapter.setMoviesData(null);
            adapter.setPostersPath(null);
            adapter.setRating(null);
            adapter.setReleaseDate(null);
            adapter.setmOverview(null);
            loadMoviesData(BASE_URL_RATED);

        }
        return super.onOptionsItemSelected(item);
    }





}
