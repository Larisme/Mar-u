package com.benjamin.dugas.maru.service;

import com.benjamin.dugas.maru.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeeting() { return meetings; }

    @Override
    public void deleteMeeting(Meeting meeting) { this.meetings.remove(meeting); }

    @Override
    public void createMeeting(Meeting meeting) { this.meetings.add(meeting); }

    @Override
    public List<Meeting> filterMeetingByLocation(String location) {
        List<Meeting> filterMeeting = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getLocation().equals(location))
                filterMeeting.add(meeting);
        }
        return filterMeeting ;
    }

    @Override
    public List<Meeting> filterMeetingByHour( int hour ) {
        List<Meeting> filterMeeting = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getHour() == hour)
                filterMeeting.add(meeting);
        }
        return filterMeeting;
    }

}
