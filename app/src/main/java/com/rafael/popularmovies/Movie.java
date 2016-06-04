package com.rafael.popularmovies;

public class Movie {

    private final String imageResource;
    private final String rating;
    private final String releaseDate;
    private final String description;

    public Movie (String imageResource, String rating, String releaseDate, String description) {
        this.imageResource = imageResource;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.description = description;
    }

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
}
