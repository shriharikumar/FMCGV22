package com.bizsoft.fmcgv2.dataobject;

/**
 * Created by shri on 9/8/17.
 */

public class User {
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String SalRefCode ="0000A";
    public  String SRRefCode="0000B";
    public  String SORefCode="0000C";

    public String getSalRefCode() {
        return SalRefCode;
    }

    public void setSalRefCode(String salRefCode) {
        SalRefCode = salRefCode;
    }

    public String getSRRefCode() {
        return SRRefCode;
    }

    public void setSRRefCode(String SRRefCode) {
        this.SRRefCode = SRRefCode;
    }

    public String getSORefCode() {
        return SORefCode;
    }

    public void setSORefCode(String SORefCode) {
        this.SORefCode = SORefCode;
    }

    String UserId;
    Double Id;

    public Double getId() {
        return Id;
    }

    public void setId(Double id) {
        Id = id;
    }
}
