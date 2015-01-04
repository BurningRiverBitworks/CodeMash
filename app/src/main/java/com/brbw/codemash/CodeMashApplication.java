package com.brbw.codemash;

import android.app.Application;

import com.brbw.codemash.models.SessionList;
import com.brbw.codemash.models.UserPreferences;
import com.brbw.codemash.util.network.SimpleJsonRequester;

public class CodeMashApplication extends Application {

    private static SessionList sessionList;
    private static UserPreferences userPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        userPreferences = new UserPreferences(getBaseContext());
    }

    public synchronized static SessionList sessionListInstance() {
        if (sessionList == null) {
            sessionList = new SessionList(new CodeMashService(new SimpleJsonRequester()), userPreferencesInstance());
        }
        return sessionList;
    }

    public synchronized static UserPreferences userPreferencesInstance() {
        return userPreferences;
    }
}
