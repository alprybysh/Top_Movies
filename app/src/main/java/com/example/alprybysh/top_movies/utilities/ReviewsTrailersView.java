package com.example.alprybysh.top_movies.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.alprybysh.top_movies.Movie;
import com.example.alprybysh.top_movies.R;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aprybysh on 4/29/17.
 */

public class ReviewsTrailersView extends AsyncTask<Integer, Void, Movie> {


    private Context mContext;

    private ArrayList<String> list_review;
    private ArrayList<String> list_author;
    private ArrayList<String> list_video;
    private ArrayList<String> list_name_video;
    private Movie mMovies;

    private ParseJsonUtil parseJsonUtil;

    private ProgressBar mLoadingIndicator;

    public static final int LOAD_ID_REVIEWS = 103;
    public static final int LOAD_ID_TRAILERS = 101;
    private int loadId;
    private int mMovieID;

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String REVIEWURL = "/reviews";
    private static final String TRAILERSURL = "/videos";


    public ReviewsTrailersView(Context context, int movieID) {
        mContext = context;
        mMovieID = movieID;
        mLoadingIndicator = (ProgressBar) ((Activity)mContext).findViewById(R.id.pb_loading_indicator_detail);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Movie doInBackground(Integer... params) {

        if (params.length == 0) {
            return null;
        }

        mMovies = new Movie();
        loadId = params[0];

        try {

            parseJsonUtil = new ParseJsonUtil();
            switch (loadId) {
                case (LOAD_ID_REVIEWS): {
                    URL moviesRequestUrl = NetworkUtils.buildUrl(NetworkUtils
                            .buildUrlReviews(BASE_URL, REVIEWURL, mMovieID));
                    String jsonResopnce = NetworkUtils.getResponceFromHttpUrl(moviesRequestUrl);
                    parseJsonUtil.fetchMoviesData(jsonResopnce);
                    mMovies = parseJsonUtil.getReviewsData();
                    break;
                }
                case LOAD_ID_TRAILERS: {
                    URL moviesRequestUrl = NetworkUtils.buildUrl(NetworkUtils
                            .buildUrlReviews(BASE_URL, TRAILERSURL, mMovieID));
                    String jsonResopnce = NetworkUtils.getResponceFromHttpUrl(moviesRequestUrl);
                    parseJsonUtil.fetchMoviesData(jsonResopnce);
                    mMovies = parseJsonUtil.getTrailersData();
                    break;

                    //  return mMovies;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return mMovies;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        switch (loadId) {
            case LOAD_ID_TRAILERS: {
                setTrailers();
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                break;
            }
            case LOAD_ID_REVIEWS: {

                setReviews();
                // mMovie = null;
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }

    public void setReviews() {


        list_review = mMovies.getmReviews();
        list_author = mMovies.getmAuthor();

        //      View v = getLayoutInflater().inflate(R.layout.details_of_movie, null);


        LinearLayout ll = (LinearLayout) ((Activity) mContext).findViewById(R.id.review_layout_child);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);


        for (int i = 0; i < list_review.size(); i++) {
            TextView review = new TextView(mContext);
            TextView author = new TextView(mContext);
            review.setLayoutParams(params);
            review.setPadding(Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_small)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_large)));
            review.setTextSize(mContext.getResources().getDimension(R.dimen.text_size_review));
            review.setTextColor(ContextCompat.getColor(mContext, R.color.colorOfText));
            review.setText(list_review.get(i));
            author.setLayoutParams(params);
            author.setPadding(Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_large)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_small)));
            author.setTextSize(mContext.getResources().getDimension(R.dimen.text_size_review));
            author.setTextColor(ContextCompat.getColor(mContext, R.color.colorOfText));
            author.setText(list_author.get(i));
            ll.addView(author);
            ll.addView(review);

        }

    }

    public void setTrailers() {
        list_video = mMovies.getmTrailers();
        list_name_video = mMovies.getmNameTrailers();

        LinearLayout ll = (LinearLayout) ((Activity) mContext).findViewById(R.id.trailers_layout_child);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        for (int i = 0; i < list_video.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(mContext);

            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            View view = new View(mContext);
            final Button video = new Button(mContext);
            TextView name_video = new TextView(mContext);

            video.setLayoutParams(params);
            video.setPadding(Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_small)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_large)));
            video.setTextSize(mContext.getResources().getDimension(R.dimen.text_size_review));
            video.setTextColor(ContextCompat.getColor(mContext, R.color.colorOfText));
            video.setText(list_video.get(i));
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(NetworkUtils.buildTrailerUri(video.getText().toString()));
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, video.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });

            name_video.setLayoutParams(params);
            name_video.setPadding(Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_large)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_medium)),
                    Math.round(mContext.getResources().getDimension(R.dimen.padding_small)));
            name_video.setTextSize(mContext.getResources().getDimension(R.dimen.text_size_review));
            name_video.setTextColor(ContextCompat.getColor(mContext, R.color.colorOfText));
            name_video.setText(list_name_video.get(i));

            view.setBackgroundColor(ContextCompat.getColor(mContext,
                    R.color.colorOfText));
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.MATCH_PARENT, 4));
            linearLayout.addView(name_video);
            linearLayout.addView(video);
            ll.addView(view);
            ll.addView(linearLayout);

        }


    }


}
