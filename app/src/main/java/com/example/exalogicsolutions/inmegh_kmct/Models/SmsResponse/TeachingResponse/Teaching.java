package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.TeachingResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teaching {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    private Integer check_box = 0;

    public Integer getCheck_box() {
        return check_box;
    }

    public void setCheck_box(Integer check_box) {
        this.check_box = check_box;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
