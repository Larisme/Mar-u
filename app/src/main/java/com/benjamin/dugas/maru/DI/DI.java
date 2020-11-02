package com.benjamin.dugas.maru.DI;

import com.benjamin.dugas.maru.service.DummyMeetingApiService;
import com.benjamin.dugas.maru.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() { return service; }

    public static MeetingApiService getNewInstanceApiService() { return new DummyMeetingApiService(); }

}
