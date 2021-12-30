package com.denis.myapplication.data;

import java.util.List;

public class User {
    private long id;
    private String userName;
    private String userLastName;
    private String password;
    private String userType;
    private String userEmail;
    private String userLogin;
    private List<Task> userTasks;

    public User (){

    }

    public User(long id, String userName, String userLastName, String password, String userEmail, String userLogin, List<Task> tasksList) {
        this.id = id;
        this.userName = userName;
        this.userLastName = userLastName;
        this.password = password;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userTasks = tasksList;
    }

//    public User(String userLogin, String userName, String userLastName, String password, String userEmail) {
//        this.userLogin = userLogin;
//        this.userName = userName;
//        this.userLastName = userLastName;
//        this.password = password;
////        this.userType = userType;
//        this.userEmail = userEmail;
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<Task> userTasks) {
        this.userTasks = userTasks;
    }
}
