package com.rafael.popularmovies.discovery;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rafael.popularmovies.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieFetcherTask extends AsyncTask<String, Void, String>{

    private static final String TAG = MovieFetcherTask.class.getSimpleName();

    private static final String API_KEY = BuildConfig.TMDB_API_KEY;

    @Override
    protected String doInBackground(String... strings) {
        String uri = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;
        return fetchMovieSummaries(uri);
    }


    @Nullable
    private String fetchMovieSummaries(String uri) {
        HttpURLConnection urlConnection = null;
        StringBuilder buffer = null;
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

            reader.close();
        } catch (IOException e) {
            Log.e(TAG, "Error", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        String message = buffer != null ? buffer.toString() : null;
        Log.i(TAG, message);
        return message;
    }
}
