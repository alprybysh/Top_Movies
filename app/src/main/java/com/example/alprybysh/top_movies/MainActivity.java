package com.example.alprybysh.top_movies;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;


import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.example.alprybysh.top_movies.data.MoviesContract;
import com.example.alprybysh.top_movies.utilities.FetchMoviesData;
import com.example.alprybysh.top_movies.utilities.ParseJsonUtil;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesOnClickListenerHandler,
        LoaderManager.LoaderCallbacks<Cursor> {

    private MoviesAdapter adapter;
    private ArrayList<Movie> movieArrayList;

    private static final int MOVIE_LOADER_ID = 77;

    private static final String BASE_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular";
    private static final String BASE_URL_RATED = "http://api.themoviedb.org/3/movie/top_rated";


    private RecyclerView mRecyclerView;
    private ProgressBar mLoadingIndicator;
    private Movie mMovie;

    private int pageID = 1;


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

        adapter = new MoviesAdapter(this, this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(adapter);

        /*
         * The ProgressBar that will indicate to the user that we are loading data. It will be
         * hidden when no data is loading.*/
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        if (savedInstanceState != null) {

            movieArrayList = new ArrayList<>();
            movieArrayList = savedInstanceState.getParcelableArrayList("Key");
            adapter.setMoviesData(movieArrayList);
        }

        if (savedInstanceState == null) {
            FetchMoviesData fetchMoviesData = new FetchMoviesData(mLoadingIndicator, adapter, this);
            fetchMoviesData.execute(BASE_URL_POPULAR);
        }
    }

    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param movieClicked The movie that was clicked
     */
    @Override
    public void onItemClick(Movie movieClicked) {

        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra("MovieObject", movieClicked);
        startActivity(intent);

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

            FetchMoviesData fetchMoviesData = new FetchMoviesData(mLoadingIndicator, adapter, this);
            fetchMoviesData.execute(BASE_URL_POPULAR);

            pageID =1;

        }

        if (id == R.id.show_the_most_rated) {
            FetchMoviesData fetchMoviesData = new FetchMoviesData(mLoadingIndicator, adapter, this);
            fetchMoviesData.execute(BASE_URL_RATED);
            pageID = 2;

        }

        if (id == R.id.show_my_favorites) {
            getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
            pageID =3;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pageID == 3){
            getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMovie = new Movie();
        movieArrayList = new ArrayList<>();
        movieArrayList = adapter.movies;
        mMovie.setMovieArrayList(movieArrayList);
        outState.putParcelableArrayList("Key", movieArrayList);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mCursorData = null;

            @Override
            protected void onStartLoading() {
                if (mCursorData != null) {
                    deliverResult(mCursorData);

                } else {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }
            @Override
            public Cursor loadInBackground() {
                try {

                    mCursorData = getContentResolver().query(MoviesContract.MoviesEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return mCursorData;
            }

            @Override
            public void deliverResult(Cursor data) {
                super.deliverResult(data);
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        adapter.convertCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
