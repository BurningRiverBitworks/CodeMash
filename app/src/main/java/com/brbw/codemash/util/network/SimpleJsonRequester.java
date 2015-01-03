package com.brbw.codemash.util.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.brbw.codemash.util.Strings.from;

public class SimpleJsonRequester {

    public JSONArray getArray(String url) throws IOException, JSONException {
        InputStream inputStream = requestInputStringFromThe(url);
        return new JSONArray(from(inputStream));
    }

    public JSONObject getObject(String url) throws IOException, JSONException {
        InputStream inputStream = requestInputStringFromThe(url);
        return new JSONObject(from(inputStream));
    }

    private InputStream requestInputStringFromThe(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        return connection.getInputStream();
    }
}
