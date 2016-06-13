package com.rafael.popularmovies.discovery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.rafael.popularmovies.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviePosterAdapter extends BaseAdapter {

    private Context context;

    private List<Movie> movies = new ArrayList<>();

    public MoviePosterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup viewGroupParent) {
        ImageView imageView = buildViewToDisplay(currentView);
        Picasso.with(this.context).load(getImageFromResource(position)).fit().into(imageView);
        return imageView;
    }

    public void add(List<Movie> s) {
        this.movies.clear();
        this.movies.addAll(s);
        this.notifyDataSetChanged();
    }

    private String getImageFromResource(int position) {
        return movies.get(position).getImageResource();
    }

    @NonNull
    private ImageView buildViewToDisplay(View currentView) {
        ImageView imageView;
        if (isViewRecycled(currentView)) {
            imageView = buildViewFromScratch();
        } else {
            imageView = (ImageView) currentView;
        }
        return imageView;
    }

    @NonNull
    private ImageView buildViewFromScratch() {
        ImageView imageView = new ImageView(this.context);
        imageView.setLayoutParams(new GridView.LayoutParams(540, 700));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

    private boolean isViewRecycled(View currentView) {
        return currentView == null;
    }
}
