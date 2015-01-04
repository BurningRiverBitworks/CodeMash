package com.brbw.codemash.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.brbw.codemash.R;
import com.brbw.codemash.controllers.fragments.SessionDetailsFragment;
import com.brbw.codemash.models.Session;

import static com.brbw.codemash.util.Strings.urlEncode;

public class SessionDetailsActivity extends ActionBarActivity {

    private static final String LOG_TAG = "SessionDetailsActivity";
    private static final String SESSION_EXTRA = "SESSION_EXTRA";
    private Session session;

    public static Intent newIntentToStart(Context context, Session session) {
        Intent intent = new Intent(context, SessionDetailsActivity.class);
        intent.putExtra(SESSION_EXTRA, session);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = (Session) getIntent().getSerializableExtra(SESSION_EXTRA);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, SessionDetailsFragment.newInstance(session))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.session_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.session_share == item.getItemId()) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(buildTweet())));
            return true;
        }else if(android.R.id.home == item.getItemId()) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private String buildTweet() {
        String message = String.format(getString(R.string.twitter_message), session.getTitle());
        String twitterUrl = getString(R.string.twitter_url);
        return String.format(twitterUrl, urlEncode(message));
    }
}

