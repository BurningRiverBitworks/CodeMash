package com.brbw.codemash.controllers.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.brbw.codemash.R;

public class FilterDialogFragment extends DialogFragment {

    public static final String TAG = "FILTER_DIALOG";

    public static FilterDialogFragment newInstance() {
        return new FilterDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.filter_title)
                .setSingleChoiceItems(R.array.favorites, -1, null)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }
}
