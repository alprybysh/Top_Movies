package com.example.alprybysh.top_movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class DetailActivity extends AppCompatActivity {

    private String mTitle;
    private TextView mTitleView;
    private ImageView mPoster;
    private TextView mOverviewView;
    private String mOverview;
    private TextView mRatingView;
    private String mRating;
    private TextView mReleaseDateView;
    private String ReleaseDate;

   // Movie mMovie = new Movie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_movie);



        Intent intentStarted = getIntent();


        mTitleView = (TextView) findViewById(R.id.title_details);
        mPoster = (ImageView) findViewById(R.id.details_poster);
        mOverviewView = (TextView) findViewById(R.id.overview);
        mReleaseDateView = (TextView) findViewById(R.id.release_date);
        mRatingView = (TextView) findViewById(R.id.release_date);



        if (intentStarted != null){
            if (intentStarted.hasExtra(Intent.EXTRA_TEXT)){

                mTitle = intentStarted.getStringExtra(Intent.EXTRA_TEXT);
                mTitleView.setText(mTitle);
                Picasso.with(mPoster.getContext()).load(Movie.getmPath()).into(mPoster);
                mOverviewView.setText(Movie.getmOverview());
                mRatingView.setText(Movie.getmRating());
                mReleaseDateView.setText(Movie.getmReleaseDate());
            }
        }



    }
}
