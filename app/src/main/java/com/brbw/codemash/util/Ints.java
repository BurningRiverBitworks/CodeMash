package com.brbw.codemash.util;

import org.json.JSONException;
import org.json.JSONObject;

public class Ints {

    public static int intOrDefaultFrom(JSONObject json, String key, int defaultValue) {
        try {
            if (json.has(key)) return json.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
