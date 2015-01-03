package com.brbw.codemash.tasks;

import android.os.AsyncTask;

import com.brbw.codemash.CodeMashApplication;
import com.brbw.codemash.models.Day;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.models.SessionList;

import java.util.Arrays;
import java.util.List;

public class SessionRetrievalTask extends AsyncTask<Day, Void, List<Session>> {

    @Override
    protected List<Session> doInBackground(Day... day) {
        if (this.has(day)) {
            SessionList sessionList = CodeMashApplication.sessionListInstance();
            return sessionList.getSessionsFor(day[0]);
        } else {
            return Arrays.asList();
        }
    }

    private boolean has(Day[] day) {
        return day != null && day.length == 1;
    }
}
