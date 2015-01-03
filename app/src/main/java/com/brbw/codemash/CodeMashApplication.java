package com.brbw.codemash;

import android.app.Application;

import com.brbw.codemash.models.SessionList;
import com.brbw.codemash.util.network.SimpleJsonRequester;

public class CodeMashApplication extends Application {

    private static SessionList sessionList;

    public synchronized static SessionList sessionListInstance() {
        if (sessionList == null) {
            sessionList = new SessionList(new CodeMashService(new SimpleJsonRequester()));
        }
        return sessionList;
    }
}
