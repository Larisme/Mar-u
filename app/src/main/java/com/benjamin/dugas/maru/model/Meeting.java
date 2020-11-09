package com.benjamin.dugas.maru.model;

import java.util.List;

public class    Meeting {

    /** Avatar **/
    private int avatarColor;

    /** Hour **/
    private String hour;

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
    public Meeting(int avatarColor, String hour, String location, String topic,
                   List<String> participants) {
        this.avatarColor = avatarColor;
        this.hour = hour;
        this.location = location;
        this.topic = topic;
        this.participants = participants;
    }

    public int getAvatarColor() {return avatarColor; }

    public void setAvatarColor(int avatarColor) { this.avatarColor = avatarColor; }

    public String getHour() { return hour; }

    public void setHour(String hour) { this.hour = hour; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTopic() { return topic; }

    public void setTopic(String topic) { this.topic = topic; }

    public  List getParticipants() { return participants; }

    public String getParticipant(int choice) { return participants.get(choice); }

    public void setParticipants(List participants) { this.participants = participants; }


}
