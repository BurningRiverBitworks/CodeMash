package com.brbw.codemash.models;


import com.brbw.codemash.CodeMashService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SessionList {
    private final Object lock = new Object();
    private final CodeMashService service;
    private List<Session> sessions;

    public SessionList(CodeMashService service) {
        this.service = service;
    }

    public List<Session> getAllSessions() {

        if (sessions == null) {
            synchronized (lock) {
                sessions = service.getSessions();
            }
        }
        return sessions;
    }

    public List<Session> getSessionsFor(Day dayOfTheWeek) {
        List<Session> sessionsForDay = new ArrayList<>();
        for (Session session : getAllSessions()) {
            if (dayOfTheWeek.isOn(session.getSessionStartTime())) {
                sessionsForDay.add(session);
            }
        }
        return sessionsForDay;
    }

    public Session getSession(int sessionId) {
        for (Session session : getAllSessions()) {
            if (session.getId() == sessionId) {
                return session;
            }
        }
        return new Session(new JSONObject());
    }

    public List<Session> getSessionsForSpeakerId(String speakerId) {
        List<Session> speakerSessions = new ArrayList<>();
        for (Session session : getAllSessions()) {
            for (SparseSpeaker sparseSpeaker : session.getSpeakers()) {
                if (sparseSpeaker.getId().equals(speakerId)) {
                    speakerSessions.add(session);
                }
            }
        }
        return speakerSessions;
    }
}
