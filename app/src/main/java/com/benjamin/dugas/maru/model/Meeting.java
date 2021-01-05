package com.benjamin.dugas.maru.model;

import java.util.List;

public class    Meeting {

    /** Avatar **/
    private int avatarColor;

    /** Hour **/
    private int hour;
    private int minutes;

    /** Location **/
    private String location;

    /** Topic **/
    private String topic;

    /** Participants **/
    private List<String> participants;

    /**
     * Constructor
     * @param hour
     * @param location
     * @param topic
     * @param participants
     */
    public Meeting(int avatarColor, int hour, int minutes,  String location, String topic,
                   List<String> participants) {
        this.avatarColor = avatarColor;
        this.hour = hour;
        this.minutes = minutes;
        this.location = location;
        this.topic = topic;
        this.participants = participants;
    }

    public int getAvatarColor() {return avatarColor; }

    public void setAvatarColor(int avatarColor) { this.avatarColor = avatarColor; }

    public int getHour() { return hour; }

    public void setHour(int hour) { this.hour = hour; }

    public int getMinutes() {return minutes; }

    public void setMinutes(int minutes) {this.minutes = minutes;}

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTopic() { return topic; }

    public void setTopic(String topic) { this.topic = topic; }

    public  List getParticipants() { return participants; }

    public String getParticipant(int choice) { return participants.get(choice); }

    public void setParticipants(List participants) { this.participants = participants; }


}
