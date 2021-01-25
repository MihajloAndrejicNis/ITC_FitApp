package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;
import com.mihajloandrejic.fitnessapp.helper.Type;

public class Mindset implements Type {

    @SerializedName("title:")
    private String title;

    @SerializedName("background")
    private String background;

    public Mindset(String title, String background) {
        this.title = title;
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public String getBackground() {
        return background;
    }

    @Override
    public int getType() {
        return 2;
    }
}
