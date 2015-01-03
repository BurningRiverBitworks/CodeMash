package com.brbw.codemash;


import android.util.Log;

import com.brbw.codemash.models.Session;
import com.brbw.codemash.models.Speaker;
import com.brbw.codemash.util.network.SimpleJsonRequester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeMashService {

    private static final String LOG_TAG = "CodeMashService";

    public final static String SERVICE_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private final static String SERVICE_BASE_URL = "https://cmprod-speakers.azurewebsites.net/api/";

    private final SimpleJsonRequester simpleJsonRequester;


    public CodeMashService(SimpleJsonRequester simpleJsonRequester) {
        this.simpleJsonRequester = simpleJsonRequester;
    }

    public List<Session> getSessions() {
        try {
            return Session.listFrom(jsonArrayCalling("sessionsdata"));
        } catch (IOException | JSONException e) {
            Log.w(LOG_TAG, e);
        }
        return new ArrayList<>();
    }

    public List<Speaker> getSpeakers() {
        try {
            return Speaker.speakerListFrom(jsonArrayCalling("speakersdata"));
        } catch (IOException | JSONException e) {
            Log.w(LOG_TAG, e);
        }
        return new ArrayList<>();
    }

    private JSONArray jsonArrayCalling(String endpoint) throws IOException, JSONException {
        return simpleJsonRequester.getArray(SERVICE_BASE_URL + endpoint);
    }

    public Speaker getSpeakerWith(String id) {
        JSONObject speakerJson;
        try {
            speakerJson = simpleJsonRequester.getObject(SERVICE_BASE_URL + "speakersdata/" + id);
        } catch (IOException | JSONException e) {
            speakerJson = new JSONObject();
            Log.w(LOG_TAG, e);
        }
        return new Speaker(speakerJson);
    }

    public Session getSessionWith(int id) {
        JSONObject speakerJson;
        try {
            speakerJson = simpleJsonRequester.getObject(SERVICE_BASE_URL + "sessionsdata/" + id);
        } catch (IOException | JSONException e) {
            speakerJson = new JSONObject();
            Log.w(LOG_TAG, e);
        }
        return new Session(speakerJson);
    }
}
