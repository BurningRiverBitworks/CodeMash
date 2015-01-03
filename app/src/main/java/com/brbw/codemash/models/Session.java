package com.brbw.codemash.models;

import com.brbw.codemash.CodeMashService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.brbw.codemash.util.Dates.dateFrom;
import static com.brbw.codemash.util.Ints.intOrDefaultFrom;
import static com.brbw.codemash.util.Json.arrayOrEmpty;
import static com.brbw.codemash.util.Strings.nullSafeStringFrom;
import static com.brbw.codemash.util.Strings.nullSafeStringListFrom;

public class Session implements Comparable<Session>, Serializable {

    private int id;
    private Date sessionStartTime;
    private Date sessionEndTime;
    private List<String> rooms;
    private String title;
    private String abstrakt;
    private String sessionType;
    private List<String> tags;
    private List<SparseSpeaker> speakers;
    private boolean favorited;

    public Session(JSONObject json) {
        this.id = intOrDefaultFrom(json, "Id", -1);
        this.sessionStartTime = dateFrom(json, "SessionStartTime", CodeMashService.SERVICE_DATE_TIME_FORMAT);
        this.sessionEndTime = dateFrom(json, "SessionEndTime", CodeMashService.SERVICE_DATE_TIME_FORMAT);
        this.rooms = nullSafeStringListFrom(json, "Rooms");
        this.title = nullSafeStringFrom(json, "Title");
        this.abstrakt = nullSafeStringFrom(json, "Abstract");
        this.sessionType = nullSafeStringFrom(json, "SessionType");
        this.tags = nullSafeStringListFrom(json, "Tags");
        this.speakers = SparseSpeaker.sparseSpeakerListFrom(arrayOrEmpty(json, "Speakers"));
    }

    public static List<Session> listFrom(JSONArray sessionArray) throws JSONException {
        List<Session> sessions = new ArrayList<>();
        for (int i = 0; i < sessionArray.length(); i++) {
            sessions.add(new Session(sessionArray.getJSONObject(i)));
        }
        return sessions;
    }

    public int getId() {
        return id;
    }

    public Date getSessionStartTime() {
        return sessionStartTime;
    }

    public Date getSessionEndTime() {
        return sessionEndTime;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract() {
        return abstrakt;
    }

    public String getSessionType() {
        return sessionType;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<SparseSpeaker> getSpeakers() {
        return speakers;
    }

    public boolean isPast() {
        Date now = new Date();
        return now.after(this.getSessionStartTime());
    }

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public int compareTo(Session anotherSession) {
        int comparisonResult = this.getSessionStartTime().compareTo(anotherSession.getSessionStartTime());
        return comparisonResult != 0 ? comparisonResult : this.getTitle().compareTo(anotherSession.getTitle());
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void isFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
