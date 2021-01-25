package com.mihajloandrejic.fitnessapp.netutils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String BASE_URL = "https://api.mocki.io/v1/a2039548/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

}
