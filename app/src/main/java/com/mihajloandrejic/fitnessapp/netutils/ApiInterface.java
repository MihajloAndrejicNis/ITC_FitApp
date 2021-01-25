package com.mihajloandrejic.fitnessapp.netutils;


import com.mihajloandrejic.fitnessapp.datamodels.Event;
import com.mihajloandrejic.fitnessapp.datamodels.Events;
import com.mihajloandrejic.fitnessapp.datamodels.Mindset;
import com.mihajloandrejic.fitnessapp.datamodels.Recipe;
import com.mihajloandrejic.fitnessapp.datamodels.Tasks;
import com.mihajloandrejic.fitnessapp.datamodels.User;
import com.mihajloandrejic.fitnessapp.datamodels.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("users/1")
    Call<User> getUsers();

    @GET("week")
    Call<Events> getEvents();

    @GET("week")
    Call<List<Event>> getEvent();

    @GET("events/{event}")
    Call<Tasks> getTasks( @Path("event") int eventId);




}
