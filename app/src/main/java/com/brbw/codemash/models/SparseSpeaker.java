package com.brbw.codemash.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.brbw.codemash.util.Json.objectAtIndexOrEmpty;
import static com.brbw.codemash.util.Strings.nullSafeStringFrom;

public class SparseSpeaker implements Comparable<SparseSpeaker>, Serializable {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String biography;
    protected String gravatarUrl;

    public SparseSpeaker(JSONObject json) {
        this.gravatarUrl = nullSafeStringFrom(json, "GravatarUrl");
        this.biography = nullSafeStringFrom(json, "Biography");
        this.firstName = nullSafeStringFrom(json, "FirstName");
        this.lastName = nullSafeStringFrom(json, "LastName");
        this.id = nullSafeStringFrom(json, "Id");
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBiography() {
        return biography;
    }

    public String getGravatarUrl() {
        return gravatarUrl;
    }

    public static List<SparseSpeaker> sparseSpeakerListFrom(JSONArray speakersArray) {
        List<SparseSpeaker> speakers = new ArrayList<>();
        for (int i = 0; i < speakersArray.length(); i++) {
            speakers.add(new SparseSpeaker(objectAtIndexOrEmpty(speakersArray, i)));
        }
        return speakers;
    }

    @Override
    public int compareTo(SparseSpeaker speaker) {
        return this.getLastName().compareTo(speaker.getLastName());
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
