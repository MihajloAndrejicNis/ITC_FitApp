package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;
import com.mihajloandrejic.fitnessapp.helper.Type;

import java.util.List;

public class Workout implements Type {


    @SerializedName("title")
    private String title;

    @SerializedName("background")
    private String background;

    @SerializedName("time")
    private int time;

    @SerializedName("equipment")
    private List<Equipment> equipment;


    public Workout(String title, String background, int time, List<Equipment> equipment) {
        this.title = title;
        this.background = background;
        this.time = time;
        this.equipment = equipment;


    }


    public String getTitle() {
        return title;
    }

    public String getBackground() {
        return background;
    }

    public int getTime() {
        return time;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    @Override
    public int getType() {
        return 0;
    }
}
