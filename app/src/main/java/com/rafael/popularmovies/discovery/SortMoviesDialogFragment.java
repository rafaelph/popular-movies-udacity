package com.rafael.popularmovies.discovery;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.rafael.popularmovies.R;

import java.util.List;

import static com.rafael.popularmovies.SortPreferenceHelper.SHARED_PREFERENCE_NAME;
import static com.rafael.popularmovies.SortPreferenceHelper.SORT_PREFERENCE;
import static java.util.Arrays.asList;

public class SortMoviesDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort movies by")
                .setItems(R.array.sort_by, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        List<String> sortPreference = asList(getResources().getStringArray(R.array.sort_by));
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString(SORT_PREFERENCE, sortPreference.get(i)).apply();
                    }
                });

        return builder.create();
    }
}
