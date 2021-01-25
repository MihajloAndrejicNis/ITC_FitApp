package com.mihajloandrejic.fitnessapp.datamodels;

import com.google.gson.annotations.SerializedName;

public class User {


    @SerializedName("name")
    private String userName;
    @SerializedName("image")
    private String userImage;
    @SerializedName("points")
    private int userPoints;
    @SerializedName("workout_level")
    private  String userLevel;

    public User(String userName, String userImage, int userPoints, String userLevel) {
        this.userName = userName;
        this.userImage = userImage;
        this.userPoints = userPoints;
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public String getUserLevel() {
        return userLevel;
    }
}
