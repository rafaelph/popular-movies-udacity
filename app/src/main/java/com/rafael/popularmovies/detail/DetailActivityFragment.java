package com.rafael.popularmovies.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafael.popularmovies.Movie;
import com.rafael.popularmovies.R;
import com.squareup.picasso.Picasso;

import static com.rafael.popularmovies.Movie.MOVIE_EXTRA;

public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView movieThumbnail = (ImageView) rootView.findViewById(R.id.movieThumbnailView);

        Bundle extrasFromIntent = getActivity().getIntent().getExtras();
        if (extrasFromIntent != null) {
            Movie movie = getMovieFromParcelable(extrasFromIntent);
            TextView movieTitle = (TextView) rootView.findViewById(R.id.movieTitleTextView);
            TextView description = (TextView) rootView.findViewById(R.id.synopsisText);
            movieTitle.setText(movie.getMovieTitle());
            description.setText(movie.getDescription());
            Picasso.with(getActivity()).load(movie.getImageResource()).into(movieThumbnail);
        } else {
            Picasso.with(getActivity()).load(R.mipmap.ic_launcher).into(movieThumbnail);
        }

        return rootView;
    }

    private Movie getMovieFromParcelable(Bundle extrasFromIntent) {
        return extrasFromIntent.getParcelable(MOVIE_EXTRA);
    }
}
