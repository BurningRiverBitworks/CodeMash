package com.brbw.codemash.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.brbw.codemash.R;
import com.brbw.codemash.controllers.fragments.SessionDetailsFragment;
import com.brbw.codemash.models.Session;

public class SessionDetailsActivity extends ActionBarActivity {

    private static final String LOG_TAG = "SessionDetailsActivity";
    private static final String SESSION_EXTRA = "SESSION_EXTRA";

    public static Intent newIntentToStart(Context context, Session session) {
        Intent intent = new Intent(context, SessionDetailsActivity.class);
        intent.putExtra(SESSION_EXTRA, session);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        Session session = (Session) getIntent().getSerializableExtra(SESSION_EXTRA);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, SessionDetailsFragment.newInstance(session))
                .commit();
    }
}
