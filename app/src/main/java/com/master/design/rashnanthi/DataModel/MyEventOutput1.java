package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class MyEventOutput1 {
    private ArrayList<MyEventData1> data;
    private String success;

    public ArrayList<MyEventData1> getData() {

        return data;

    }

    public void setData(ArrayList<MyEventData1> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
