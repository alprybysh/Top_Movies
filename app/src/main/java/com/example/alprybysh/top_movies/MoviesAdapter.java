package com.example.alprybysh.top_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alprybysh.top_movies.utilities.ParseJsonUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aprybysh on 3/27/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private ArrayList<String> mData;
    private ArrayList<String> mPostersPath;
    private ArrayList<String> mRating;
    private  ArrayList<String> mOverview;
    private  ArrayList<String> mReleaseDate;

    Movie mMovie;

    private final MoviesOnClickListenerHandler mClickHnadler;


    public interface MoviesOnClickListenerHandler {
        void onItemClick(String itemClicked);
    }


    public MoviesAdapter(MoviesOnClickListenerHandler clickListenerHandler) {

        mClickHnadler = clickListenerHandler;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;
        public ImageView mPoster;


        public MoviesViewHolder(View itemView) {
            super(itemView);
            mPoster = (ImageView) itemView.findViewById(R.id.display_poster);
            mTextView = (TextView) itemView.findViewById(R.id.display_information);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            mMovie = new Movie();

            int adapterPosition = getAdapterPosition();
            String itemClicked = mData.get(adapterPosition);
            String itemClicked1 = mPostersPath.get(adapterPosition);
            String itemClicked2 = mRating.get(adapterPosition);
            String itemClicked3 = mOverview.get(adapterPosition);
            String itemClicked4 = mReleaseDate.get(adapterPosition);
            mMovie.setmTitle(itemClicked);
            mMovie.setmPath(itemClicked1);
            mMovie.setmRating(itemClicked2);
            mMovie.setmOverview(itemClicked3);
            mMovie.setmReleaseDate(itemClicked4);
            mClickHnadler.onItemClick(itemClicked);

        }
    }


    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListIte = R.layout.items_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListIte, parent, shouldAttachToParentImmediately);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder moviesViewHolder, int position) {
      //  Context context = moviesViewHolder.mPoster.getContext();

        String moviesToDisplay = mData.get(position);
        String posterToDisplay = mPostersPath.get(position);
        Picasso.with(moviesViewHolder.mPoster.getContext()).load(posterToDisplay).into(moviesViewHolder.mPoster);
        moviesViewHolder.mTextView.setText(moviesToDisplay);
    }


    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();

    }

    public void setMoviesData (ArrayList<String> data) {

            mData = data;
            notifyDataSetChanged();

    }

    public void setPostersPath(ArrayList<String> data) {

            mPostersPath = data;
            notifyDataSetChanged();

    }

    public void setRating (ArrayList<String> data){
        mRating = data;
        notifyDataSetChanged();
    }
    public void setReleaseDate (ArrayList<String> data){
        mReleaseDate = data;
        notifyDataSetChanged();
    }

    public void setmOverview(ArrayList<String> data){
        mOverview = data;
        notifyDataSetChanged();
    }




}
