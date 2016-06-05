package com.rafael.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final String MOVIE_EXTRA = "movie";

    private final String imageResource;
    private final String rating;
    private final String releaseDate;
    private final String description;
    private final String movieTitle;

    public Movie (String movieTitle, String imageResource, String rating, String releaseDate, String description) {
        this.imageResource = imageResource;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.description = description;
        this.movieTitle = movieTitle;
    }

    protected Movie(Parcel in) {
        movieTitle = in.readString();
        imageResource = in.readString();
        rating = in.readString();
        releaseDate = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieTitle);
        dest.writeString(imageResource);
        dest.writeString(rating);
        dest.writeString(releaseDate);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getImageResource() {
        return imageResource;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
