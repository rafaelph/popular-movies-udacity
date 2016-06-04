package com.rafael.popularmovies.discovery;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rafael.popularmovies.R;
import com.rafael.popularmovies.detail.DetailActivity;

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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long adapterItemPosition) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
