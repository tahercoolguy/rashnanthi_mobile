package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class CountryOutput {

    private ArrayList<CountryData> data;
    private String success;

    public ArrayList<CountryData> getData() {

        return data;

    }

    public void setData(ArrayList<CountryData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
