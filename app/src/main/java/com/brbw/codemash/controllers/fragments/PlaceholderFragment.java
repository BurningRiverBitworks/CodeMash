package com.brbw.codemash.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brbw.codemash.R;
import com.brbw.codemash.models.Day;

public class PlaceholderFragment extends Fragment {

    private static final String LOG_TAG = "PlaceHolderFragment";
    private static final String ARG_DAY = "day";

    private Day day;

    public static Fragment newInstance(Day day) {
        PlaceholderFragment fragment = new PlaceholderFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        if (day != null) {
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(day.name());
        }
        return rootView;
    }

}
