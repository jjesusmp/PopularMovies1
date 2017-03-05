package com.example.android.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies1.model.MovieDto;
import com.example.android.popularmovies1.utils.Constants;
import com.squareup.picasso.Picasso;

public class MovieAbstractActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mSypnosis;
    private ImageView mImage;
    private TextView mUserRating;
    private TextView mReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_abstract);
        Intent i = getIntent();
        MovieDto movie = (MovieDto)i.getSerializableExtra("MovieDto");

        //Init items
        this.mTitle=(TextView)findViewById(R.id.abstractTitleTextView);
        this.mTitle.setText(movie.getTitle());

        this.mSypnosis=(TextView)findViewById(R.id.abstractSypnosisTextView);
        this.mSypnosis.setText(movie.getSypnosis());

        this.mImage=(ImageView)findViewById(R.id.abstractPosterImageView);
        Picasso.with(this.mImage.getContext())
                .load(Constants.BASE_URL_IMAGE+Constants.PARAM_SIZE+movie.getImage())
                .into(this.mImage);

        this.mUserRating=(TextView)findViewById(R.id.abstractUserRatingTextView);
        this.mUserRating.setText(movie.getUser_rating());

        this.mReleaseDate=(TextView)findViewById(R.id.abstractReleaseDateTextView);
        this.mReleaseDate.setText(movie.getRelease_date());
    }
}
