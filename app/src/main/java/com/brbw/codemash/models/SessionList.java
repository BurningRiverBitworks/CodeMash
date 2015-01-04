package com.brbw.codemash.models;


import com.brbw.codemash.CodeMashService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SessionList {
    private final Object lock = new Object();
    private final CodeMashService service;
    private final UserPreferences userPreferences;
    private List<Session> sessions;

    public SessionList(CodeMashService service, UserPreferences userPreferences) {
        this.service = service;
        this.userPreferences = userPreferences;
    }

    public List<Session> getAllSessions() {

        if (sessions == null) {
            synchronized (lock) {
                sessions = service.getSessions();
            }
        }
        addFavoritedStateToSessions(sessions);
        return sessions;
    }

    private void addFavoritedStateToSessions(List<Session> sessions) {
        List<Integer> favoriteSessionIds = userPreferences.getFavoriteSessionIds();
        for (Session session : sessions) {
            if (favoriteSessionIds.contains(session.getId())) {
                session.isFavorited(true);
            }
        }
    }

    public List<Session> getSessionsFor(Day dayOfTheWeek) {
        List<Session> sessionsForDay = new ArrayList<>();
        for (Session session : getAllSessions()) {
            if (dayOfTheWeek.isOn(session.getSessionStartTime())) {
                sessionsForDay.add(session);
            }
        }
        return filterByFavoritedPreference(sessionsForDay);
    }

    private List<Session> filterByFavoritedPreference(List<Session> sessionsForDay) {
        boolean favoritesEnabled = userPreferences.isFavoritesOnly();
        List<Session> allSessionsAfterFilters = new ArrayList<>();
        for (Session session : sessionsForDay) {
            if(favoritesEnabled) {
                if(session.isFavorited()) {
                    allSessionsAfterFilters.add(session);
                }
            }else {
                allSessionsAfterFilters.add(session);
            }
        }
        return allSessionsAfterFilters;
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
