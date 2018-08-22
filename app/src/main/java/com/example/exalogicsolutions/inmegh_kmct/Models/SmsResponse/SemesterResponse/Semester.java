package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.SemesterResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Semester {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("semester")
    @Expose
    private String semester;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
