package com.brbw.codemash.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import com.brbw.codemash.R;
import com.brbw.codemash.models.Day;

import java.util.ArrayList;
import java.util.List;

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

        List<String> emptyList = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,emptyList);

        setListAdapter(arrayAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CharSequence message = getText(R.string.empty_session_list_message);
        setEmptyText(message);
    }

    private boolean hasArguments() {
        return getArguments() != null && getArguments().containsKey(ARG_DAY);
    }

}
