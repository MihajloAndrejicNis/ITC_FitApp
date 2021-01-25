package com.mihajloandrejic.fitnessapp;

import com.mihajloandrejic.fitnessapp.datamodels.Tasks;
import com.mihajloandrejic.fitnessapp.datamodels.User;

public class App {

    private static User user;
    private static int eventId;
    private static int weeklyP;
    private static int monthlyP;

    public static int getWeeklyP() {
        return weeklyP;
    }

    public static void setWeeklyP(int weeklyP) {
        App.weeklyP = weeklyP;
    }

    public static int getMonthlyP() {
        return monthlyP;
    }

    public static void setMonthlyP(int monthlyP) {
        App.monthlyP = monthlyP;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }


    public static int getEventId() {
        return eventId;
    }

    public static void setEventId(int eventId) {
        App.eventId = eventId;
    }
}
