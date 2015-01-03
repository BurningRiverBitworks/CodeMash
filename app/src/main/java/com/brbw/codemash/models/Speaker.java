package com.brbw.codemash.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.brbw.codemash.util.Strings.nullSafeStringFrom;


public class Speaker extends SparseSpeaker {

    private String twitterLink;
    private String gitHubLink;
    private String linkedInProfile;
    private String blogUrl;
    private List<Session> sessions;

    public Speaker(JSONObject json) {
        super(json);
        this.twitterLink = nullSafeStringFrom(json, "TwitterLink");
        this.gitHubLink = nullSafeStringFrom(json, "GitHubLink");
        this.linkedInProfile = nullSafeStringFrom(json, "LinkedInProfile");
        this.blogUrl = nullSafeStringFrom(json, "BlogUrl");
    }

    public static List<Speaker> speakerListFrom(JSONArray array) throws JSONException {
        List<Speaker> speakers = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            speakers.add(new Speaker(array.getJSONObject(i)));
        }
        return speakers;
    }

    void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions != null ? sessions : new ArrayList<Session>();
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

}
