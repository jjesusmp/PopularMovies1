package com.example.android.popularmovies1.utils;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.popularmovies1.model.MovieDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Jesus on 28/02/2017.
 */

public final class MovieJsonUtils {

        /**
         * This method parses JSON from a web response and returns an array of Strings
         * describing the weather over various days from the forecast.
         * <p/>
         * Later on, we'll be parsing the JSON into structured data within the
         * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
         * now, we just convert the JSON into human-readable strings.
         *
         * @param MoviesListJsonResponseStr JSON response from server
         *
         * @return Array of Strings describing weather data
         *
         * @throws JSONException If JSON data cannot be properly parsed
         */
        public static ArrayList<MovieDto> getMovieListFromJson(String MoviesListJsonResponseStr)
                throws JSONException {

            final String TITLE = "title";

            final String IMAGE = "poster_path";

            final String SYPNOSIS = "overview";

            final String USER_RATING = "vote_average";

            final String RELEASE_DATE = "release_date";

            final String OWM_MESSAGE_CODE = "cod"; //error code

        /* String array to hold each movie String */
            ArrayList<MovieDto> parsedMovieList = new ArrayList<MovieDto>();


            JSONObject ResultsJson = new JSONObject(MoviesListJsonResponseStr);

            /* Is there an error? */
            if (ResultsJson.has(OWM_MESSAGE_CODE)) {
                int errorCode = ResultsJson.getInt(OWM_MESSAGE_CODE);

                switch (errorCode) {
                    case HttpURLConnection.HTTP_OK:
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                        return null;
                    default:
                    /* Server probably down */
                        return null;
                }
            }
            JSONArray moviesData = ResultsJson.getJSONArray("results");

            for (int i=0; i<moviesData.length(); i++) {
                MovieDto movie = new MovieDto();
                JSONObject movieInfo = moviesData.getJSONObject(i);
                movie.setTitle(movieInfo.getString(TITLE));
                movie.setImage(movieInfo.getString(IMAGE));
                movie.setSypnosis(movieInfo.getString(SYPNOSIS));
                movie.setUser_rating(movieInfo.getString(USER_RATING));
                movie.setRelease_date(movieInfo.getString(RELEASE_DATE));
                parsedMovieList.add(movie);
            }
            Log.d("TAG",parsedMovieList.toString());

            return parsedMovieList;
        }

    }

