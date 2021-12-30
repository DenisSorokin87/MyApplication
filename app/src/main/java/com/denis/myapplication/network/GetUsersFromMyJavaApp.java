package com.denis.myapplication.network;

import com.denis.myapplication.data.User;
import com.denis.myapplication.data.WorkersListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetUsersFromMyJavaApp {


    @GET("/MavenFisrtMeeting-1.0/rest/check")
    Call<ArrayList<User>> getUsersFromJavaApp();
}
