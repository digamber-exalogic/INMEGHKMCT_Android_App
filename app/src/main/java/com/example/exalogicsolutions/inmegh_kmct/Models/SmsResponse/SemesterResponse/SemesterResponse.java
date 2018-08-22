package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.SemesterResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SemesterResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("semesters")
    @Expose
    private List<Semester> semesters = null;
    @SerializedName("messge")
    @Expose
    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
