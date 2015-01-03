package com.brbw.codemash.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.brbw.codemash.R;
import com.brbw.codemash.controllers.adapters.SessionListAdapter;
import com.brbw.codemash.models.Day;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.tasks.SessionRetrievalTask;

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

        if (this.hasArgumentsFor(ARG_DAY)) {
            day = (Day) getArguments().getSerializable(ARG_DAY);

            SessionRetrievalTask task = new SessionRetrievalTask() {
                @Override
                protected void onPostExecute(List<Session> sessions) {
                    super.onPostExecute(sessions);
                    fillListWith(sessions);
                }
            };

            task.execute(day);
        }
    }

    private void fillListWith(List<Session> sessions) {
        SessionListAdapter adapter = new SessionListAdapter(getActivity(), sessions);
        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CharSequence message = getText(R.string.empty_session_list_message);
        setEmptyText(message);
    }

    private boolean hasArgumentsFor(String arg) {
        return getArguments() != null && getArguments().containsKey(arg);
    }

}
