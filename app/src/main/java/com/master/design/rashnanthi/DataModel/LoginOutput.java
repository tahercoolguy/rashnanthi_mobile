package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class LoginOutput {
    private String success;
    private ArrayList<OtpScreenData> data = new ArrayList<OtpScreenData>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public ArrayList<OtpScreenData> getData() {
        return data;
    }
    public void setData(ArrayList<OtpScreenData> data) {
        this.data = data;
    }
}
