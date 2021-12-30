package com.denis.myapplication;

import android.app.Application;
import androidx.room.Room;
import com.denis.myapplication.dao.AppDataBase;
import com.denis.myapplication.dao.AppTaskDataBase;
import com.denis.myapplication.dao.AppUserTasksDataBase;
import com.denis.myapplication.network.UserInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    public static MyApplication instance;

    private AppDataBase usersDatabase;
    private AppTaskDataBase appTaskDataBase;
    private AppUserTasksDataBase userTasksDataBase;
    private  Gson gson;

    private  Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        usersDatabase = Room.databaseBuilder(this, AppDataBase.class, "userDatabase")
        .allowMainThreadQueries().build();

        appTaskDataBase = Room.databaseBuilder(this, AppTaskDataBase.class, "TaskDatabase")
                .allowMainThreadQueries().build();

        userTasksDataBase = Room.databaseBuilder(this, AppUserTasksDataBase.class, "UserTasksDatabase")
                .allowMainThreadQueries().build();


        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return usersDatabase;
    }

    public AppTaskDataBase getAppTaskDataBase() { return appTaskDataBase; }

    public AppUserTasksDataBase getUserTasksDataBase() {
        return userTasksDataBase;
    }

    public UserInterface getUsersRetrofit(){
        return retrofit.create(UserInterface.class);
    }
}
