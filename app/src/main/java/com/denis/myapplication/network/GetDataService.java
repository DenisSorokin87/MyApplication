package com.denis.myapplication.network;

import com.denis.myapplication.data.WorkersListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/65gb/static/raw/master/testTask.json")
    Call<WorkersListResponse> getAllWorkers();
}
