package com.rafael.popularmovies.discovery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.rafael.popularmovies.Movie;
import com.rafael.popularmovies.R;

import java.util.List;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public class MoviePosterAdapter extends BaseAdapter {

    private Context context;

    private List<Movie> sampleThumbnails = asList(
            new Movie(valueOf(R.drawable.sample_0), null, null, null),
            new Movie(valueOf(R.drawable.sample_1), null, null, null),
            new Movie(valueOf(R.drawable.sample_2), null, null, null),
            new Movie(valueOf(R.drawable.sample_3), null, null, null),
            new Movie(valueOf(R.drawable.sample_4), null, null, null),
            new Movie(valueOf(R.drawable.sample_5), null, null, null),
            new Movie(valueOf(R.drawable.sample_6), null, null, null),
            new Movie(valueOf(R.drawable.sample_7), null, null, null),
            new Movie(valueOf(R.drawable.sample_0), null, null, null),
            new Movie(valueOf(R.drawable.sample_1), null, null, null),
            new Movie(valueOf(R.drawable.sample_2), null, null, null),
            new Movie(valueOf(R.drawable.sample_3), null, null, null)
    );

    public MoviePosterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sampleThumbnails.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup viewGroupParent) {
        ImageView imageView = buildViewToDisplay(currentView);
        imageView.setImageResource(getImageFromResource(position));
        return imageView;
    }

    private int getImageFromResource(int position) {
        return Integer.valueOf(sampleThumbnails.get(position).getImageResource());
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
        imageView.setLayoutParams(new GridView.LayoutParams(480, 480));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(8, 8, 8, 8);
        return imageView;
    }

    private boolean isViewRecycled(View currentView) {
        return currentView == null;
    }
}
