package com.example.alprybysh.top_movies;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alprybysh.top_movies.data.MoviesContract;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aprybysh on 3/27/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private final Context mContext;

    public ArrayList <Movie> movies;

    Movie mMovie;

    private final MoviesOnClickListenerHandler mClickHnadler;


    private Cursor mCursor;

    /**
     * The interface that receives onClick messages.
     */

    public interface MoviesOnClickListenerHandler {
        void onItemClick(Movie itemClicked);
    }

    /**
     * Creates a MoviesAdapter.
     *
     * @param clickListenerHandler The on-click handler for this adapter. This single handler is called
     *                             when an item is clicked.
     */
    public MoviesAdapter(@NonNull Context context, MoviesOnClickListenerHandler clickListenerHandler) {

        mClickHnadler = clickListenerHandler;
        mContext = context;
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
         //   mTextView = (TextView) itemView.findViewById(R.id.display_information);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            mMovie = new Movie();
            mMovie = movies.get( getAdapterPosition());
            mMovie.setmTitle(mMovie.getmTitle());
            mMovie.setmPath(mMovie.getmPath());
            mMovie.setmRating(mMovie.getmRating());
            mMovie.setmOverview(mMovie.getmOverview());
            mMovie.setmReleaseDate(mMovie.getmReleaseDate());
            mMovie.setmID(mMovie.getmID());

            mClickHnadler.onItemClick(mMovie);

        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup that these ViewHolders are contained within.
     * @param viewType If your RecyclerView has more than one type of item (which ours doesn't) you
     *                 can use this viewType integer to provide a different layout. See
     *                 {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                 for more details.
     * @return A new MoviesAdapterViewHolder that holds the View for each list item
     */
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.items_layout, parent, false);

        view.setFocusable(true);

        return new MoviesViewHolder(view);

    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param moviesViewHolder The ViewHolder which should be updated to represent the
     *                         contents of the item at the given position in the data set.
     * @param position         The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MoviesViewHolder moviesViewHolder, int position) {
        mMovie = new Movie();
        mMovie = movies.get(position);
        String posterToDisplay = mMovie.getmPath();
        Picasso.with(moviesViewHolder.mPoster.getContext())
                .load(posterToDisplay)
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(moviesViewHolder.mPoster);

    }

    /**
     * This method simply returns the number of items to display.
     */
    @Override
    public int getItemCount() {

        if (movies == null) {
            return 0;
        }
        return movies.size();

    }

    public void setMoviesData (ArrayList<Movie> movies){

        this.movies = movies;
        notifyDataSetChanged();
    }


//    void swapCursor(Cursor newCursor) {
//        mCursor = newCursor;
//        notifyDataSetChanged();
//    }


    public void convertCursor(Cursor cursor) {

        movies = new ArrayList();


        for (int i = 0; i < cursor.getCount(); i++) {
            mMovie = new Movie();
            cursor.moveToPosition(i);
            mMovie.setmTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_TITLE)));
            mMovie.setmID(cursor.getInt(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_IDENTIFIER)));
            mMovie.setmPath(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_POSTER)));
            mMovie.setmOverview(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_OVERVIEW)));
            mMovie.setmRating(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_USER_RATING)));
            mMovie.setmReleaseDate(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE)));
            movies.add(mMovie);

        }
        notifyDataSetChanged();

    }


}

