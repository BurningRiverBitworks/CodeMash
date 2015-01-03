package com.brbw.codemash.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Ints {

    private static final String LOG_TAG = "Ints";

    public static int intOrDefaultFrom(JSONObject json, String key, int defaultValue) {
        try {
            if (json.has(key)) return json.getInt(key);
        } catch (JSONException e) {
            Log.w(LOG_TAG, e);
        }
        return defaultValue;
    }

}
