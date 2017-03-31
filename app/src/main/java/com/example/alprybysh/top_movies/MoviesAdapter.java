package com.example.alprybysh.top_movies;

import android.content.Context;
import android.net.Uri;
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

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aprybysh on 3/27/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private ArrayList<String> mData;
    private ArrayList<String> mPostersPath;
    private ArrayList<String> mRating;
    private ArrayList<String> mOverview;
    private ArrayList<String> mReleaseDate;

    Movie mMovie;

    private final MoviesOnClickListenerHandler mClickHnadler;

    /**
     * The interface that receives onClick messages.
     */
    public interface MoviesOnClickListenerHandler {
        void onItemClick(String itemClicked);
    }

    /**
     * Creates a MoviesAdapter.
     *
     * @param clickListenerHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public MoviesAdapter(MoviesOnClickListenerHandler clickListenerHandler) {

        mClickHnadler = clickListenerHandler;
    }
    /**
     * Cache of the children views for a forecast list item.
     */
    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;
        public ImageView mPoster;

        /**
         * This gets called by the child views during a click.
         *
         * @param itemView The View that was clicked
         */


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

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new MoviesAdapterViewHolder that holds the View for each list item
     */
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListIte = R.layout.items_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListIte, parent, shouldAttachToParentImmediately);
        return new MoviesViewHolder(view);
    }
    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param moviesViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MoviesViewHolder moviesViewHolder, int position) {

        String moviesToDisplay = mData.get(position);
        String posterToDisplay = mPostersPath.get(position);
        Picasso.with(moviesViewHolder.mPoster.getContext())
                .load(posterToDisplay)
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(moviesViewHolder.mPoster);
        moviesViewHolder.mTextView.setText(moviesToDisplay);
    }

    /**
     * This method simply returns the number of items to display.
     *
     */
    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();

    }
    /**
     * These methods are used to set the movie data on a adapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new ForecastAdapter to display it.
     *
     * @param data The new movie data to be displayed.
     */
    public void setTitles (ArrayList<String> data) {

        mData = data;
        notifyDataSetChanged();
    }

    public void setPostersPath(ArrayList<String> data) {

        mPostersPath = data;
        notifyDataSetChanged();

    }

    public void setRating(ArrayList<String> data) {
        mRating = data;
        notifyDataSetChanged();
    }

    public void setReleaseDate(ArrayList<String> data) {
        mReleaseDate = data;
        notifyDataSetChanged();
    }

    public void setmOverview(ArrayList<String> data) {
        mOverview = data;
        notifyDataSetChanged();
    }


}
