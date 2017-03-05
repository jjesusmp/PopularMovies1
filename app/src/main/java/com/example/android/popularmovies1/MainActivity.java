package com.example.android.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies1.model.MovieDto;
import com.example.android.popularmovies1.utils.Constants;
import com.example.android.popularmovies1.utils.MovieJsonUtils;
import com.example.android.popularmovies1.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieListAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.movie_list);

        mErrorMessageDisplay = (TextView) findViewById(R.id.ml_error_message_display);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);

        mRecyclerView.setAdapter(mMovieAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieListData(Constants.orderByPopular);//orderByPopular is the default order
    }

    private void loadMovieListData(String sortBy) {
        showMovieListDataView();
        new FetchMovieListTask().execute(sortBy);
    }

    @Override
    public void onClick(MovieDto movieInfoForThisItem) {
        Context context = this;
        Toast.makeText(context, movieInfoForThisItem.getTitle(), Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(MainActivity.this,MovieAbstractActivity.class);
        intent.putExtra("MovieDto", movieInfoForThisItem);
        startActivity(intent);


    }

    private void showMovieListDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }


    public class FetchMovieListTask extends AsyncTask<String, Void, ArrayList<MovieDto>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<MovieDto> doInBackground(String... params) {

            String orderBy = params[0];
            URL url = NetworkUtils.buildAPIUrl(orderBy);

            try {
                String jsonMovieListResponse = NetworkUtils
                        .getResponseFromHttpUrl(url);

                ArrayList<MovieDto> simpleMovieListData = MovieJsonUtils.getMovieListFromJson(jsonMovieListResponse);

                return simpleMovieListData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDto> listMovieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (listMovieData != null) {
                showMovieListDataView();
                mMovieAdapter.setMovieData(listMovieData);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSortRating) {
            mMovieAdapter.setMovieData(null);
            loadMovieListData(Constants.orderByTopRated);
            return true;
        } if (id == R.id.menuSortPopular) {
            mMovieAdapter.setMovieData(null);
            loadMovieListData(Constants.orderByPopular);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
