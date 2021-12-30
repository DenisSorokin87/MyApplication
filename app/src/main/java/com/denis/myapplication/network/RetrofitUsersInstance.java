package com.denis.myapplication.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUsersInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080";

    public static Retrofit getRetrofitInstance(Context context){

        if(retrofit == null){

            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }else return retrofit;
    }

}
