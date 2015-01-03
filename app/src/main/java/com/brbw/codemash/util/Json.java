package com.brbw.codemash.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {

    public static JSONArray arrayOrEmpty(JSONObject json, String key) {
        if (json.has(key)) {
            try {
                return json.getJSONArray(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new JSONArray();
    }

    public static JSONObject objectAtIndexOrEmpty(JSONArray jsonArray, int index) {
        if (jsonArray.length() > index) {
            try {
                return jsonArray.getJSONObject(index);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new JSONObject();
    }


}
