package com.rafael.popularmovies.discovery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rafael.popularmovies.BuildConfig;
import com.rafael.popularmovies.R;
import com.rafael.popularmovies.detail.DetailActivity;

import static com.rafael.popularmovies.Movie.MOVIE_EXTRA;
import static com.rafael.popularmovies.SortPreferenceHelper.GetQueryParameterForSorting;
import static com.rafael.popularmovies.SortPreferenceHelper.SHARED_PREFERENCE_NAME;
import static com.rafael.popularmovies.SortPreferenceHelper.SORT_PREFERENCE;
import static com.rafael.popularmovies.SortPreferenceHelper.SORT_PREFERENCE_DEFAULT;


public class MainDiscoveryScreenFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String API_KEY_QUERY = "api_key";

    private MoviePosterAdapter moviePosterAdapter;

    public MainDiscoveryScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_discovery_screen, container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView);

        this.moviePosterAdapter = new MoviePosterAdapter(getActivity().getApplicationContext());
        gridView.setAdapter(this.moviePosterAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long adapterItemPosition) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(MOVIE_EXTRA, moviePosterAdapter.getItem(position));
                startActivity(intent);
            }
        });

        getActivity().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).registerOnSharedPreferenceChangeListener(this);

        fetchMovies();

        return rootView;
    }

    private void fetchMovies() {
        MovieFetcherTask fetcherTask = new MovieFetcherTask(this.moviePosterAdapter);
        String urlString = buildMovieUri(GetQueryParameterForSorting(getSortPreference()));
        Log.i("TAG", urlString);
        fetcherTask.execute(urlString);
    }

    @NonNull
    private String buildMovieUri(String sortPreference) {
        Uri.Builder builder = new Uri.Builder();
        builder.encodedPath(TMDB_BASE_URL + sortPreference);
        builder.appendQueryParameter(API_KEY_QUERY, BuildConfig.TMDB_API_KEY);
        return builder.build().toString();
    }

    @NonNull
    private String getSortPreference() {
        return getActivity().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).getString(SORT_PREFERENCE, SORT_PREFERENCE_DEFAULT);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String preferenceName) {
        fetchMovies();
    }
}
