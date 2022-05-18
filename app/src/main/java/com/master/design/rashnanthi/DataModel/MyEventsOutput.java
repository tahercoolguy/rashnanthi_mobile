package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class MyEventsOutput {
    private String success;
    private ArrayList<MyEventImageData> data = new ArrayList<MyEventImageData>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public ArrayList<MyEventImageData> getData() {
        return data;
    }
    public void setData(ArrayList<MyEventImageData> data) {
        this.data = data;
    }
}
