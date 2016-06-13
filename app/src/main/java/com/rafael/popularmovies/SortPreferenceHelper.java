package com.rafael.popularmovies;

public class SortPreferenceHelper {

    public static final String SHARED_PREFERENCE_NAME = "SORTING";
    public static final String SORT_PREFERENCE = "SORT";

    public static final String SORT_PREFERENCE_DEFAULT = "Popularity";

    public static String GetQueryParameterForSorting(String sortPreference) {
        if ("Ratings".equalsIgnoreCase(sortPreference)) {
            return "top_rated";
        } else {
            return "popular";
        }
    }

}
