package com.brbw.codemash;

import com.brbw.codemash.models.Day;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.models.SessionList;
import com.brbw.codemash.models.UserPreferences;
import com.brbw.codemash.util.Dates;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionListTest extends CodeMashTestCase {

    private SessionList sessionList;
    private List<Session> stubSessions;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        stubSessions = getStubSessions();
        CodeMashService stubService = mock(CodeMashService.class);
        UserPreferences stubUserPreferences = mock(UserPreferences.class);
        given(stubService.getSessions()).willReturn(stubSessions);
        sessionList = new SessionList(stubService, stubUserPreferences);
    }

    public void testThatItGivesMeAllSessions() {
        List<Session> sessions = sessionList.getAllSessions();

        assertEquals(stubSessions.size(), sessions.size());
    }

    public void testThatItFiltersSessionsByDay() {
        List<Session> tuesdaysSessions = sessionList.getSessionsFor(Day.TUESDAY);
        List<Session> wednesdaySessions = sessionList.getSessionsFor(Day.WEDNESDAY);
        List<Session> thursdaySessions = sessionList.getSessionsFor(Day.THURSDAY);
        List<Session> fridaySessions = sessionList.getSessionsFor(Day.FRIDAY);

        assertEquals(2, tuesdaysSessions.size());
        assertEquals(1, wednesdaySessions.size());
        assertEquals(1, thursdaySessions.size());
        assertEquals(1, fridaySessions.size());
    }


    private List<Session> getStubSessions() {
        Session tuesdaySession1 = mock(Session.class);
        Session tuesdaySession2 = mock(Session.class);
        Session wednesdaySession1 = mock(Session.class);
        Session thursdaySession = mock(Session.class);
        Session fridaySession = mock(Session.class);
        when(tuesdaySession1.getSessionStartTime())
                .thenReturn(Dates.dateOrDefault(CodeMashService.SERVICE_DATE_TIME_FORMAT, "2015-01-06T08:30:00", 0));
        when(tuesdaySession2.getSessionStartTime())
                .thenReturn(Dates.dateOrDefault(CodeMashService.SERVICE_DATE_TIME_FORMAT, "2015-01-06T13:30:00", 0));
        when(wednesdaySession1.getSessionStartTime())
                .thenReturn(Dates.dateOrDefault(CodeMashService.SERVICE_DATE_TIME_FORMAT, "2015-01-07T13:30:00", 0));
        when(thursdaySession.getSessionStartTime())
                .thenReturn(Dates.dateOrDefault(CodeMashService.SERVICE_DATE_TIME_FORMAT, "2015-01-08T13:30:00", 0));
        when(fridaySession.getSessionStartTime())
                .thenReturn(Dates.dateOrDefault(CodeMashService.SERVICE_DATE_TIME_FORMAT, "2015-01-09T13:30:00", 0));
        return Arrays.asList(tuesdaySession1, tuesdaySession2, wednesdaySession1, thursdaySession, fridaySession);
    }


}
