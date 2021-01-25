package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;
import com.mihajloandrejic.fitnessapp.helper.Type;

public class Tip implements Type {
    @SerializedName("workout_tip")
    private String workout_tip;

    public Tip(String workout_tip) {
        this.workout_tip = workout_tip;
    }

    public String getWorkout_tip() {
        return workout_tip;
    }

    @Override
    public int getType() {
        return 3;
    }
}
