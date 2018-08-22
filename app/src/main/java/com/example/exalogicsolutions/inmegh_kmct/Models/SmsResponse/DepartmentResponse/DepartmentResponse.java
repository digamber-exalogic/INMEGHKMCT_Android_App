package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.DepartmentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartmentResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("departments")
    @Expose
    private List<Department> departments = null;
    @SerializedName("messge")
    @Expose
    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
