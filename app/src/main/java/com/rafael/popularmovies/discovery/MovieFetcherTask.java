package com.rafael.popularmovies.discovery;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rafael.popularmovies.BuildConfig;
import com.rafael.popularmovies.Movie;
import com.rafael.popularmovies.MovieJsonParser;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieFetcherTask extends AsyncTask<String, Void, List<Movie>>{

    private static final String TAG = MovieFetcherTask.class.getSimpleName();
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;

    private MoviePosterAdapter moviePosterAdapter;

    public MovieFetcherTask(MoviePosterAdapter moviePosterAdapter) {
        this.moviePosterAdapter = moviePosterAdapter;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        String uri = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;
        return fetchMovieSummaries(uri);
    }

    @Override
    protected void onPostExecute(List<Movie> s) {
        this.moviePosterAdapter.add(s);
        super.onPostExecute(s);
    }

    @Nullable
    private List<Movie> fetchMovieSummaries(String uri) {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection urlConnection = null;
        StringBuilder buffer;
        try {
            //Fetch Data
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Parse data
            InputStream inputStream = urlConnection.getInputStream();
            buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                return null;
            }

            movies = new MovieJsonParser(buffer.toString()).getMovies();

            reader.close();
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        for (Movie movie: movies) {
            Log.i(TAG, movie.getMovieTitle());
        }

        return movies;
    }
}
