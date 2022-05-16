package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class GetCoachByCountryOutput {

    private ArrayList<GetCoachByCountryData> data;
    private String success;

    public ArrayList<GetCoachByCountryData> getData() {

        return data;

    }

    public void setData(ArrayList<GetCoachByCountryData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
