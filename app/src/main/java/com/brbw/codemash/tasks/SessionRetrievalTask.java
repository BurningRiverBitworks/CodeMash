package com.brbw.codemash.tasks;

import android.os.AsyncTask;

import com.brbw.codemash.CodeMashService;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.util.network.SimpleJsonRequester;

import java.util.List;

public class SessionRetrievalTask extends AsyncTask<Void, Void, List<Session>> {

    @Override
    protected List<Session> doInBackground(Void... params) {
        CodeMashService service = new CodeMashService(new SimpleJsonRequester());
        return service.getSessions();
    }
}
