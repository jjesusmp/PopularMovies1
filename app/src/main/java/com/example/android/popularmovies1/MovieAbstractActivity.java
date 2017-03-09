package com.example.android.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies1.model.MovieDto;
import com.example.android.popularmovies1.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAbstractActivity extends AppCompatActivity {

    @BindView(R.id.abstractTitleTextView) TextView mTitle;
    @BindView(R.id.abstractPosterImageView) ImageView mImage;
    @BindView(R.id.abstractSypnosisTextView) TextView mSypnosis;
    @BindView(R.id.abstractUserRatingTextView) TextView mUserRating;
    @BindView(R.id.abstractReleaseDateTextView) TextView mReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_abstract);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        if(intent.hasExtra("MovieDto")) {
            MovieDto movie = (MovieDto) intent.getParcelableExtra("MovieDto");

            mTitle.setText(movie.getTitle());
            mSypnosis.setText(movie.getSypnosis());
            Picasso.with(mImage.getContext())
                    .load(Constants.BASE_URL_IMAGE + Constants.PARAM_SIZE + movie.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(mImage);
            mUserRating.setText(movie.getUser_rating());
            mReleaseDate.setText(movie.getRelease_date());
        }
        ButterKnife.bind(this);
    }
}
