package com.denis.myapplication.network

import com.denis.myapplication.data.User
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.ArrayList

interface UserInterface {


    @GET("/MavenFisrtMeeting-1.0/rest/check")
    fun getUsersFromJavaApp(): Call<ArrayList<User?>>?

    @GET("/MavenFisrtMeeting-1.0/rest/check/{id}")
    fun getSomeQuery(@Path("id") id: Int, @Query("name") name: String?): Call<ArrayList<String?>?>?

    @POST("/MavenFisrtMeeting-1.0/rest/test")
    fun sendUser(@Body user: User?): Call<ArrayList<String?>?>?


}

