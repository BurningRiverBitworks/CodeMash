package com.brbw.codemash.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Strings {

    private static final String LOG_TAG = "Strings";

    public static String nullSafeStringFrom(JSONObject json, String key) {
        try {
            if (json.has(key)) return json.getString(key);
        } catch (JSONException e) {
            Log.w(LOG_TAG, e);
        }
        return "";
    }

    public static String nullSafeStringFrom(String string) {
        return isNullOrEmpty(string) ? "" : string;
    }

    public static List<String> nullSafeStringListFrom(JSONObject json, String key) {
        List<String> rooms = new ArrayList<>();
        JSONArray array = getJsonArray(json, key);
        for (int i = 0; i < array.length(); i++) {
            try {
                rooms.add(array.getString(i));
            } catch (JSONException e) {
                Log.w(LOG_TAG, e);
            }
        }
        return rooms;
    }

    private static JSONArray getJsonArray(JSONObject json, String key) {
        JSONArray array = new JSONArray();
        try {
            if (json.has(key)) array = json.getJSONArray(key);
        } catch (JSONException e) {
            Log.w(LOG_TAG, e);
        }
        return array;
    }

    public static boolean isNullOrEmpty(String output) {
        return output == null || output.trim().isEmpty();
    }

    public static String from(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                output.append(line);
            }
        } finally {
            if (reader != null) reader.close();
        }

        return output.toString();
    }

    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.w(LOG_TAG, e);
            return "";
        }
    }
}
