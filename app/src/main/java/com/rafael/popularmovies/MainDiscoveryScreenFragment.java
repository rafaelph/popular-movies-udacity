package com.rafael.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainDiscoveryScreenFragment extends Fragment {

    public MainDiscoveryScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_discovery_screen, container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView);
        gridView.setAdapter(new MoviePosterAdapter(getActivity().getApplicationContext()));

        return rootView;
    }
}
