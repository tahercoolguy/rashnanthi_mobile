package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class OtpScreenOutput {
    private String success;
    private ArrayList<String> mobiledata = new ArrayList<String>();
    private ArrayList<OtpScreenData> data = new ArrayList<OtpScreenData>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public ArrayList<String> getMobiledata() {
        return mobiledata;
    }
    public void setMobiledata(ArrayList<String> mobiledata) {
        this.mobiledata = mobiledata;
    }
    public ArrayList<OtpScreenData> getData() {
        return data;
    }
    public void setData(ArrayList<OtpScreenData> data) {
        this.data = data;
    }
}
