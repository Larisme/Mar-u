package com.benjamin.dugas.maru.events;

import com.benjamin.dugas.maru.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting) { this.meeting = meeting; }
}
