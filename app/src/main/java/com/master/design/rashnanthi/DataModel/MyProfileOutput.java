package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class MyProfileOutput {
    private String success;
    private ArrayList<MyProfileData> data = new ArrayList<MyProfileData>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public ArrayList<MyProfileData> getData() {
        return data;
    }
    public void setData(ArrayList<MyProfileData> data) {
        this.data = data;
    }
}
