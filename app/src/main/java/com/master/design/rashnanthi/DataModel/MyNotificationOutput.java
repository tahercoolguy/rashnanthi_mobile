package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class MyNotificationOutput {

    private String success;
    private ArrayList<MyNotificationOutputData> data = new ArrayList<MyNotificationOutputData>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public ArrayList<MyNotificationOutputData> getData() {
        return data;
    }
    public void setData(ArrayList<MyNotificationOutputData> data) {
        this.data = data;
    }
}
