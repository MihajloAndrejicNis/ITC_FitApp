package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("day")
    private String day;

    @SerializedName("eventId")
    private int eventId;

    public Event(String day, int eventId) {
        this.day = day;
        this.eventId = eventId;
    }

    public String getDay() {
        return day;
    }

    public int getEventId() {
        return eventId;
    }
}
