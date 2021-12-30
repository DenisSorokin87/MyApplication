package com.denis.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Worker {

    @SerializedName("f_name")
    private String firstName;
    @SerializedName("l_name")
    private String lastName;
    private String birthday;
    @SerializedName("avatr_url")
    private String avatarURL;
    @SerializedName("specialty")
    private List<Speciality> specialities;

    public Worker(String firstName, String lastName, String birthday, String avatarURL, List<Speciality> specialities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarURL = avatarURL;
        this.specialities = specialities;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
