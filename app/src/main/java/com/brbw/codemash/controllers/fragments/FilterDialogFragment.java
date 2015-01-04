package com.brbw.codemash.controllers.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.brbw.codemash.CodeMashApplication;
import com.brbw.codemash.R;
import com.brbw.codemash.models.UserPreferences;

public class FilterDialogFragment extends DialogFragment {

    public static final String TAG = "FILTER_DIALOG";
    private DialogInterface.OnClickListener listener;

    public static FilterDialogFragment newInstance() {
        return new FilterDialogFragment();
    }

    public void setOnClickListener(DialogInterface.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final UserPreferences userPreferences = CodeMashApplication.userPreferencesInstance();
        int favoritesOptionSelected = userPreferences.isFavoritesOnly() ? 1 : 0;

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.filter_title)
                .setSingleChoiceItems(R.array.favorites, favoritesOptionSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 1) {
                            userPreferences.isFavoritesOnly(true);
                        } else {
                            userPreferences.isFavoritesOnly(false);
                        }
                    }
                })
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
