package com.rafael.popularmovies.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafael.popularmovies.Movie;
import com.rafael.popularmovies.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.rafael.popularmovies.Movie.MOVIE_EXTRA;
import static com.squareup.picasso.Picasso.with;
import static java.lang.String.valueOf;

public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView movieThumbnail = (ImageView) rootView.findViewById(R.id.movieThumbnailView);
        FragmentActivity currentActivity = getActivity();

        Bundle extrasFromIntent = currentActivity.getIntent().getExtras();
        if (extrasFromIntent != null) {
            Movie movie = getMovieFromParcelable(extrasFromIntent);
            populateViewsFromMovie(rootView, movie);
            with(currentActivity).load(movie.getImageResource()).into(movieThumbnail);
        } else {
            with(currentActivity).load(R.mipmap.ic_launcher).into(movieThumbnail);
        }

        return rootView;
    }

    private Movie getMovieFromParcelable(Bundle extrasFromIntent) {
        return extrasFromIntent.getParcelable(MOVIE_EXTRA);
    }

    private void populateViewsFromMovie(View rootView, Movie movie) {
        TextView movieTitle = (TextView) rootView.findViewById(R.id.movieTitleTextView);
        TextView description = (TextView) rootView.findViewById(R.id.synopsisText);
        TextView releaseYearView = (TextView) rootView.findViewById(R.id.yearText);

        movieTitle.setText(movie.getMovieTitle());
        description.setText(movie.getDescription());
        releaseYearView.setText(getReleaseYearFromMovie(movie));
    }

    private String getReleaseYearFromMovie(Movie movie) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date releaseDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(movie.getReleaseDate());
            calendar.setTime(releaseDate);
            return valueOf(calendar.get(Calendar.YEAR));

        } catch (ParseException e) {
            return "N/A";
        }
    }
}
