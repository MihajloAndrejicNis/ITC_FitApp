package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class Events {

    @SerializedName("monthly_progress")
    private int monthlyProgress;

    @SerializedName("weekly_progress")
    private int weeklyProgress;

    @SerializedName("events")
    private List<Event> events;

    public Events(int monthlyProgress, int weeklyProgress, List<Event> events) {
        this.monthlyProgress = monthlyProgress;
        this.weeklyProgress = weeklyProgress;
        this.events = events;
    }

    public int getMonthlyProgress() {
        return monthlyProgress;
    }

    public int getWeeklyProgress() {
        return weeklyProgress;
    }

    public List<Event> getEvents() {
        return events;
    }
}
