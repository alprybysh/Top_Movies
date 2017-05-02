package com.example.alprybysh.top_movies;


import android.content.Intent;

import android.database.Cursor;
import android.graphics.PorterDuff;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.alprybysh.top_movies.data.MoviesContract;
import com.example.alprybysh.top_movies.data.SetMoviesDatabase;

import com.example.alprybysh.top_movies.utilities.ReviewsTrailersView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity

{



    private int mMovieID;
    private boolean mMovieExist;
    private Cursor cursor;


    public static final int LOADER_ID_REVIEWS = 103;
    public static final int LOADER_ID_TRAILERS = 101;


    private SetMoviesDatabase setMoviesDatabase;


    private Movie mMovie;

    @BindView(R.id.users_rating)
    TextView mRatingView;
    @BindView(R.id.release_date)
    TextView mReleaseDateView;
    @BindView(R.id.overview)
    TextView mOverviewView;
    @BindView(R.id.details_poster)
    ImageView mPoster;
    @BindView(R.id.title_details)
    TextView mTitleView;
    @BindView(R.id.edit_favorite)
    Button favorite;
    @BindView(R.id.pb_loading_indicator_detail)
    ProgressBar mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_movie);


        Intent intentStarted = getIntent();

        ButterKnife.bind(this);

        Movie myParcelableObject = intentStarted.getParcelableExtra("MovieObject");

        if (intentStarted != null) {
            //if (intentStarted.hasExtra(Intent.EXTRA_TEXT)) {
            if (myParcelableObject != null) {

                // mTitle = intentStarted.getStringExtra(Intent.EXTRA_TEXT);
                mMovie = new Movie();
                mMovie = myParcelableObject;
                mTitleView.setText(mMovie.getmTitle());
                Picasso.with(mPoster.getContext())
                        .load(mMovie.getmPath())
                        .placeholder(R.drawable.download)
                        .error(R.drawable.download)
                        .into(mPoster);
                mOverviewView.setText(mMovie.getmOverview());
                mRatingView.setText(mMovie.getmRating());
                mReleaseDateView.setText(mMovie.getmReleaseDate());
                mMovieID = mMovie.getmID();
                mMovieExist = checkIfMovieExist();
                if (mMovieExist) {
                    favorite.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSavedFavorite));
                } else
                    favorite.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefaultFavorite));

                if (mMovie.getmReviews() == null || mMovie.getmNameTrailers() == null) {

                    ReviewsTrailersView reviewsTrailersView = new ReviewsTrailersView(this, mMovieID);
                    ReviewsTrailersView reviewsTrailersView1 = new ReviewsTrailersView(this, mMovieID);
                    reviewsTrailersView.execute(LOADER_ID_REVIEWS);
                    reviewsTrailersView1.execute(LOADER_ID_TRAILERS);

                }
            }
        }
    }

    public void onClickAddFavorite(View view) {


        if (mMovieExist){
            int delRow = getContentResolver().delete(MoviesContract.MoviesEntry.CONTENT_URI,
                    "identifier = " + Integer.toString(mMovieID),
                    null);

            if (delRow > 0){
                favorite.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefaultFavorite));
            }


        }else {
            setMoviesDatabase = new SetMoviesDatabase(this);
            favorite.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSavedFavorite));
            setMoviesDatabase.setMoviesData(mMovie);
            Toast.makeText(getBaseContext(), "CLICK", Toast.LENGTH_SHORT).show();

        }



    }

    public boolean checkIfMovieExist() {

        cursor = getContentResolver().query(MoviesContract.MoviesEntry.CONTENT_URI, null,
                "identifier = " + Integer.toString(mMovieID),
                null,
                null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }


}
