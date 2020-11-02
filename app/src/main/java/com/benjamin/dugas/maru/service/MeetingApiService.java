package com.benjamin.dugas.maru.service;

import com.benjamin.dugas.maru.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    List<Meeting> filterMeetingByLocation(String location);

    List<Meeting> filterMeetingByHour(String hour);
}
