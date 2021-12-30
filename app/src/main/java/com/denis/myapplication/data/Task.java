package com.denis.myapplication.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Task {

    private long id;
    private String taskType;
    private String taskDescription;
    private String taskTime;
    private String taskDate;
    private String taskUserSignature;

//    public Task (){
//
//    }
//
//    public Task(String taskType, String taskDescription,String taskDate, String taskTime) {
//        this.taskType = taskType;
//        this.taskDescription = taskDescription;
//        this.taskDate = taskDate;
//        this.taskTime = taskTime;
//
////        this.taskUserSignature = taskUserSignature;
//    }
//
//    public Task(long id, String taskType, String taskDescription, String taskDate, String taskTime ) {
//        this.id = id;
//        this.taskType = taskType;
//        this.taskDescription = taskDescription;
//        this.taskTime = taskTime;
//        this.taskDate = taskDate;
//        this.taskUserSignature = taskUserSignature;
//    }

    public Task(long id, String taskDescription, String taskDate) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskUserSignature() {
        return taskUserSignature;
    }

    public void setTaskUserSignature(String taskUserSignature) {
        this.taskUserSignature = taskUserSignature;
    }

    public LocalDate toDate(String strDate){
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
