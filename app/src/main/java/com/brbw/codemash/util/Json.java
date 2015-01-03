package com.brbw.codemash.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {

    private static final String LOG_TAG = "Json";

    public static JSONArray arrayOrEmpty(JSONObject json, String key) {
        if (json.has(key)) {
            try {
                return json.getJSONArray(key);
            } catch (JSONException e) {
                Log.w(LOG_TAG, e);
            }
        }
        return new JSONArray();
    }

    public static JSONObject objectAtIndexOrEmpty(JSONArray jsonArray, int index) {
        if (jsonArray.length() > index) {
            try {
                return jsonArray.getJSONObject(index);
            } catch (JSONException e) {
                Log.w(LOG_TAG, e);
            }
        }
        return new JSONObject();
    }


}
