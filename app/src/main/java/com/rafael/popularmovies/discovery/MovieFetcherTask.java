package com.rafael.popularmovies.discovery;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

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

public class MovieFetcherTask extends AsyncTask<String, Void, List<Movie>> {

    private static final String TAG = MovieFetcherTask.class.getSimpleName();

    private MoviePosterAdapter moviePosterAdapter;

    public MovieFetcherTask(MoviePosterAdapter moviePosterAdapter) {
        this.moviePosterAdapter = moviePosterAdapter;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        if (strings != null) {
            String uri = strings[0];
            return fetchMovieSummaries(uri);
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        this.moviePosterAdapter.add(movies);
        super.onPostExecute(movies);
    }

    @Nullable
    private List<Movie> fetchMovieSummaries(String uri) {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = new MovieJsonParser(fetchMoviesAsJsonString(uri)).getMovies();
        } catch (JSONException e) {
            Log.e(TAG, "Invalid JSON", e);
        }

        return movies;
    }

    private String fetchMoviesAsJsonString(String uri) {
        String jsonString = "";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            jsonString = getResultFromConnection(urlConnection);

        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonString;
    }


    private String getResultFromConnection(HttpURLConnection urlConnection) throws IOException {
        String result = "";
        BufferedReader reader = null;
        try {
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            if (stringBuilder.length() == 0) {
                return "";
            }
            result = stringBuilder.toString();

        } catch (NullPointerException e) {
            Log.e(TAG, "GetResultFromConnection", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }
}
