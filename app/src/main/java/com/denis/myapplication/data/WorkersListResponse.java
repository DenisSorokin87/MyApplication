package com.denis.myapplication.data;

import java.util.List;

public class WorkersListResponse {

    private List<Worker> response;

    public WorkersListResponse(List<Worker> response) {
        this.response = response;
    }

    public List<Worker> getResponse() {
        return response;
    }

    public void setResponse(List<Worker> response) {
        this.response = response;
    }
}
