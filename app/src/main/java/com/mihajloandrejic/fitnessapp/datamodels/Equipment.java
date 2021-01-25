package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;

public class Equipment {

    @SerializedName("item")
    private String item;

    public Equipment(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
