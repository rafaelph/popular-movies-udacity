package com.rafael.popularmovies.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rafael.popularmovies.R;
import com.rafael.popularmovies.detail.DetailActivity;

import static com.rafael.popularmovies.Movie.MOVIE_EXTRA;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainDiscoveryScreenFragment extends Fragment {

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
                intent.putExtra(MOVIE_EXTRA, (Parcelable) moviePosterAdapter.getItem(position));
                startActivity(intent);
            }
        });

        fetchMovieSummaries();

        return rootView;
    }

    private void fetchMovieSummaries() {
        MovieFetcherTask fetcherTask = new MovieFetcherTask(this.moviePosterAdapter);
        fetcherTask.execute("");
    }
}
