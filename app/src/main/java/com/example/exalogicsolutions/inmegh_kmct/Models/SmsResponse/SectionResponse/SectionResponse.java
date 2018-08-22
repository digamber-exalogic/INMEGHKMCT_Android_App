package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.SectionResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SectionResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;
    @SerializedName("messge")
    @Expose
    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
