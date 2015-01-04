package com.brbw.codemash.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brbw.codemash.R;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.util.ViewHelper;

public class SessionDetailsFragment extends Fragment {

    private static final String SESSION_ARGS = "SESSION_ARGS";

    public static SessionDetailsFragment newInstance(Session session) {
        SessionDetailsFragment fragment = new SessionDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(SESSION_ARGS, session);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session_details, container, false);

        if (this.hasArgsFor(SESSION_ARGS)) {
            Session session = (Session) getArguments().getSerializable(SESSION_ARGS);
            new ViewHelper(view)
                    .setText(R.id.session_title, session.getTitle());
        }
        return view;
    }

    private boolean hasArgsFor(String args) {
        return getArguments() != null && getArguments().containsKey(args);
    }
}
