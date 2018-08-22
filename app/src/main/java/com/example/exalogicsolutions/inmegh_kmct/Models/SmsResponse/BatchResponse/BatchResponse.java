package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.BatchResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BatchResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("batches")
    @Expose
    private List<Batch> batches = null;
    @SerializedName("messge")
    @Expose
    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

}
