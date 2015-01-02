package com.brbw.codemash.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import com.brbw.codemash.models.Day;

public class SessionListFragment extends ListFragment {

    private static final String LOG_TAG = "PlaceHolderFragment";
    private static final String ARG_DAY = "day";

    private Day day;

    public static Fragment newInstance(Day day) {
        SessionListFragment fragment = new SessionListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.hasArguments()) {
            day = (Day) getArguments().getSerializable(ARG_DAY);
        }
    }

    private boolean hasArguments() {
        return getArguments() != null && getArguments().containsKey(ARG_DAY);
    }

}
