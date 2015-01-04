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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static android.text.TextUtils.join;

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
                    .setText(R.id.session_title, session.getTitle())
                    .setText(R.id.session_description, session.getAbstract())
                    .setText(R.id.session_time_frame, timeFrameFrom(session))
                    .setText(R.id.session_rooms, roomsFrom(session))
                    .setText(R.id.session_tags, tagsFrom(session))
                    .setText(R.id.session_speakers, speakersFrom(session));

        }
        return view;
    }

    private String speakersFrom(Session session) {
        return join(", ", session.getSpeakers());
    }

    private String tagsFrom(Session session) {
        return join(", ", session.getTags());
    }

    private String roomsFrom(Session session) {
        return join(", ", session.getRooms());
    }

    private String timeFrameFrom(Session session) {
        DateFormat dateFormatStart = new SimpleDateFormat("EEEE hh:mm a");
        DateFormat dateFormatEnd = new SimpleDateFormat("hh:mm a");
        String start = dateFormatStart.format(session.getSessionStartTime());
        String end = dateFormatEnd.format(session.getSessionEndTime());
        return String.format("%s - %s", start, end);
    }

    private boolean hasArgsFor(String args) {
        return getArguments() != null && getArguments().containsKey(args);
    }
}
