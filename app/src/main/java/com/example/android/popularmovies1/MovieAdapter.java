package com.example.android.popularmovies1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies1.model.MovieDto;
import com.example.android.popularmovies1.utils.Constants;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by jjesusmp
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private ArrayList<MovieDto> mMovieListData;

    final private MovieListAdapterOnClickHandler mClickHandler;

    public interface MovieListAdapterOnClickHandler{
        void onClick(MovieDto s);
    }

    public MovieAdapter(MovieListAdapterOnClickHandler onClick) {
        this.mClickHandler=onClick;
    }


    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitleTextView;
        public final ImageView mPosterItemImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mTitleTextView = (TextView) view.findViewById(R.id.movie_item);
            mPosterItemImageView = (ImageView) view.findViewById(R.id.movie_item_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            MovieDto movie = mMovieListData.get(adapterPosition);
            mClickHandler.onClick(movie);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder MoviesAdapterViewHolder, int position) {
        String TitleForThisMovie = mMovieListData.get(position).getTitle();
        MoviesAdapterViewHolder.mTitleTextView.setText(TitleForThisMovie);

        Picasso.with(MoviesAdapterViewHolder.mPosterItemImageView.getContext())
                .load(Constants.BASE_URL_IMAGE+Constants.PARAM_SIZE+mMovieListData.get(position).getImage())
                .into(MoviesAdapterViewHolder.mPosterItemImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieListData) return 0;
        return mMovieListData.size();
    }

    public void setMovieData(ArrayList<MovieDto> movieData) {
        mMovieListData = movieData;
        notifyDataSetChanged();
    }
}
