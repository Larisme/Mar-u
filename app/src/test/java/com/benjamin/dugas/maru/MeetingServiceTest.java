package com.benjamin.dugas.maru;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.model.Meeting;
import com.benjamin.dugas.maru.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() { service = DI.getNewInstanceApiService(); }

    @Test
    public void addMeetingWithSuccess() {
        int count = service.getMeeting().size();
        List<String> participants = new ArrayList<String>();
        participants.add("Test1");
        participants.add("Test2");
        Meeting newMeeting = new Meeting(
                10,
                10,
                0,
                "F",
                "Test",
                participants
        );
        service.createMeeting(newMeeting);
        assertEquals(service.getMeeting().size(), count+1 );
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void filterMeetingWithSuccess() {
        service.filterMeetingByHour(10);
        int sizeHour = service.filterMeetingByHour(10).size();
        assertEquals(sizeHour, 2);
        service.filterMeetingByLocation("A");
        int sizeLocation = service.filterMeetingByLocation("A").size();
        assertEquals(sizeLocation, 1 );
    }
}