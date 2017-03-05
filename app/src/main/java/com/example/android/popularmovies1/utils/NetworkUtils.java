package com.example.android.popularmovies1.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jjesusmp on 28/02/2017.
 */

public class NetworkUtils {

    final static String BASE_URL_API = "http://api.themoviedb.org/3"; //Base URL of themovieDB
    final static String PARAM_API_KEY = "api_key";
    final static String API_KEY = "fc157eb6a9792aa1d87d0151c914692d";

    public static URL buildAPIUrl(String sortBy) {
        Uri builtUri = Uri.parse(BASE_URL_API+sortBy).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
