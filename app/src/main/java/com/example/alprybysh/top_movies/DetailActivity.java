package com.example.alprybysh.top_movies;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    private String mTitle;
    private String usersRAting = "Users rating:  ";
    private String ReleaseDate = "Release date:";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_movie);

        Intent intentStarted = getIntent();

        ButterKnife.bind(this);

        if (intentStarted != null) {
            if (intentStarted.hasExtra(Intent.EXTRA_TEXT)) {

                mTitle = intentStarted.getStringExtra(Intent.EXTRA_TEXT);
                mTitleView.setText(mTitle);
                Picasso.with(mPoster.getContext()).load(Movie.getmPath()).into(mPoster);
                mOverviewView.setText(Movie.getmOverview());
                mRatingView.setText(Movie.getmRating());
                mReleaseDateView.setText(Movie.getmReleaseDate());
                mRelease.setText(ReleaseDate);
                mUser.setText(usersRAting);
            }
        }
    }
}
