package com.example.alprybysh.top_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by aprybysh on 3/27/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private String[] mData;
    private String[] mPostersPath;


    private final MoviesOnClickListenerHandler mClickHnadler;


    public interface MoviesOnClickListenerHandler {
        void onItemClick(String s);
    }


    public MoviesAdapter( MoviesOnClickListenerHandler clickListenerHandler) {

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
            int adapterPosition = getAdapterPosition();
            String itemClicked = mData[adapterPosition];
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
        Context context = moviesViewHolder.mPoster.getContext();

        String moviesToDisplay = mData[position];
        String posterToDisplay = mPostersPath[position];
        Picasso.with(context).load(posterToDisplay).into(moviesViewHolder.mPoster);
        moviesViewHolder.mTextView.setText(moviesToDisplay);
    }



    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.length;

    }

    public  void setMoviesData (String[] moviesData){
        mData =moviesData;
        notifyDataSetChanged();
    }

    public void setPostersPath (String[] postersPath){
        mPostersPath = postersPath;
        notifyDataSetChanged();
    }




}
