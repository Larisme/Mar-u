package com.benjamin.dugas.maru.service;

import com.benjamin.dugas.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    static int red = 0xffff0000;
    static int black = 0xff181818;
    static int purple = 0xff532764;
    private static List participants = Arrays.asList("Test1", "Test2");

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(red,10, 52, "A", "test", participants),
            new Meeting(black,13, 52, "F", "Test1", participants),
            new Meeting(purple,10, 52, "F", "Test2", participants)
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETING);
    }
}
