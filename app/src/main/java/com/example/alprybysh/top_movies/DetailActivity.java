package com.example.alprybysh.top_movies;


import android.content.Intent;

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


import com.example.alprybysh.top_movies.data.SetMoviesDatabase;

import com.example.alprybysh.top_movies.utilities.ReviewsTrailersView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity

{


    private String usersRAting = "Users rating:  ";
    private String ReleaseDate = "Release date:";
    private int mMovieID;

    private ProgressBar mLoadingIndicator;


    public static final int LOADER_ID_REVIEWS = 103;
    public static final int LOADER_ID_TRAILERS = 101;


    private SetMoviesDatabase setMoviesDatabase;


    private Movie mMovie;

    @BindView(R.id.users)
    TextView mUser;
    @BindView(R.id.release)
    TextView mRelease;
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
                mRelease.setText(ReleaseDate);
                mUser.setText(usersRAting);
                mMovieID = mMovie.getmID();
                favorite.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefaultFavorite));
                mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator_detail);

                if (mMovie.getmReviews() == null || mMovie.getmNameTrailers() == null) {

                    ReviewsTrailersView reviewsTrailersView = new ReviewsTrailersView(this, mMovieID );
                    ReviewsTrailersView reviewsTrailersView1 = new ReviewsTrailersView(this, mMovieID );


                    reviewsTrailersView.execute(LOADER_ID_REVIEWS);
                   reviewsTrailersView1.execute(LOADER_ID_TRAILERS);

                }
            }
        }
    }

    public void onClickAddFavorite(View view) {

        setMoviesDatabase = new SetMoviesDatabase(this);

        setMoviesDatabase.setMoviesData(mMovie);

        Toast.makeText(getBaseContext(), "CLICK", Toast.LENGTH_SHORT).show();


    }


}
