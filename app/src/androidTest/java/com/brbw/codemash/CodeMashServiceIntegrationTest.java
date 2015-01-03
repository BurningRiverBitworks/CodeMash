package com.brbw.codemash;

import android.test.AndroidTestCase;

import com.brbw.codemash.models.Session;
import com.brbw.codemash.models.Speaker;
import com.brbw.codemash.util.network.SimpleJsonRequester;

import java.util.List;

import static com.brbw.codemash.util.Strings.isNullOrEmpty;

public class CodeMashServiceIntegrationTest extends AndroidTestCase {

    public void testThatItProvidesAListOfSessions() {
        CodeMashService codeMashService = new CodeMashService(new SimpleJsonRequester());

        List<Session> sessions = codeMashService.getSessions();

        assertTrue(sessions.size() > 0);
        assertFalse(isNullOrEmpty(sessions.get(0).getTitle()));
    }

    public void testThatItProvidesAListOfSpeakers() {
        CodeMashService codeMashService = new CodeMashService(new SimpleJsonRequester());

        List<Speaker> speakers = codeMashService.getSpeakers();

        assertTrue(speakers.size() > 0);
        assertFalse(isNullOrEmpty(speakers.get(0).getFirstName()));
    }

    public void testThatItProvidesASingleSpeaker() {
        CodeMashService codeMashService = new CodeMashService(new SimpleJsonRequester());
        String expectedId = "2574ca8c-a7a8-436a-96b4-d799ac475671";

        Speaker speaker = codeMashService.getSpeakerWith(expectedId);

        assertEquals(expectedId, speaker.getId());
        assertEquals("Dave", speaker.getFirstName());
        assertEquals("Shah", speaker.getLastName());
    }

    public void testThatItProvidesASingleSession() {
        CodeMashService codeMashService = new CodeMashService(new SimpleJsonRequester());
        int expectedId = 2658;

        Session session = codeMashService.getSessionWith(expectedId);

        assertEquals(expectedId, session.getId());
        assertEquals("You’re Going to Build an Android App at CodeMash! (Part 1)", session.getTitle());
    }
}

