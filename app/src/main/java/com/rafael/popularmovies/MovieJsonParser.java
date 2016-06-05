package com.rafael.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class MovieJsonParser {

    private final String rawJsonMovies;

    private List<Movie> movies = new ArrayList<>(20);

    public MovieJsonParser(String rawJsonMovies) throws JSONException {
        this.rawJsonMovies = rawJsonMovies;

        JSONObject jsonObject = new JSONObject(rawJsonMovies);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        this.movies = buildMovies(jsonArray);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovie(int position) {
        return movies.get(position);
    }

    private List<Movie> buildMovies(JSONArray jsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject movieJsonObject = jsonArray.getJSONObject(i);
            String movieTitle = movieJsonObject.getString("original_title");
            String imageResource = "http://image.tmdb.org/t/p/w185" + movieJsonObject.getString("poster_path");
            String description = movieJsonObject.getString("overview");
            String rating = valueOf(movieJsonObject.getDouble("vote_average"));
            String releaseDate = valueOf(movieJsonObject.getString("release_date"));
            movies.add(new Movie(movieTitle, imageResource, rating, releaseDate, description));
        }
        return movies;
    }
}
