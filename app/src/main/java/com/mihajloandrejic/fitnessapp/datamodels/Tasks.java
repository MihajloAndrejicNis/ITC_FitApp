package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;
import com.mihajloandrejic.fitnessapp.helper.Type;

public class Tasks {


    @SerializedName("workout")
    private Workout workouts;

    @SerializedName("recipe")
    private Recipe recipes;

    @SerializedName("mindset")
    private Mindset mindset;

    @SerializedName("workout_tip")
    private String workoutTip;

    @SerializedName("completed")
    private boolean completed;


    public Tasks(Workout workouts, Recipe recipes, Mindset mindset, String workoutTip, boolean completed) {
        this.workouts = workouts;
        this.recipes = recipes;
        this.mindset = mindset;
        this.workoutTip = workoutTip;
        this.completed = completed;

    }


    public Workout getWorkouts() {
        return workouts;
    }

    public Recipe getRecipes() {
        return recipes;
    }

    public Mindset getMindset() {
        return mindset;
    }

    public String getWorkoutTip() {
        return workoutTip;
    }

    public boolean isCompleted() {
        return completed;
    }
}
