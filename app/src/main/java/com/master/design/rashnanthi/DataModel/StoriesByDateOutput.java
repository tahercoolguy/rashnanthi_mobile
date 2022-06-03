package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class StoriesByDateOutput {

    private ArrayList<StoriesByDateData> data;
    private String success;

    public ArrayList<StoriesByDateData> getData() {

        return data;

    }

    public void setData(ArrayList<StoriesByDateData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
