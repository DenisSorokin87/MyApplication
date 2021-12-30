package com.denis.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://gitlab.65apps.com";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }else return retrofit;
    }
}
