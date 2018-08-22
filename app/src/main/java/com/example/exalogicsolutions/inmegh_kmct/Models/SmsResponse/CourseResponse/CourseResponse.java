package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.CourseResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("response")
    @Expose
    private List<Course> response = null;
    @SerializedName("messge")
    @Expose
    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Course> getResponse() {
        return response;
    }

    public void setResponse(List<Course> response) {
        this.response = response;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
