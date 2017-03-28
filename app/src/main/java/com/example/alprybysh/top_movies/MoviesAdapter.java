package com.example.alprybysh.top_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aprybysh on 3/27/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private String[] mData = new String[0];
    private String[] mMoviesData;
    private final MoviesOnClickListenerHandler mClickHnadler;





    public MoviesAdapter(Context context, String[] data, MoviesOnClickListenerHandler clickListenerHandler) {
        mData = data;
        mClickHnadler = clickListenerHandler;
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

        String moviesToDisplay = mData[position];
        moviesViewHolder.mTextView.setText(moviesToDisplay);


    }

    @Override
    public int getItemCount() {
//        if (mMoviesData == null) return 0;
//        return mMoviesData.length;
        return mData.length;

    }

    public interface MoviesOnClickListenerHandler {
        void onItemClick(String s);
    }



    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;

        public MoviesViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.display_information);

            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String weatherForDay = mData[adapterPosition];
            mClickHnadler.onItemClick(weatherForDay);

        }
    }


}
