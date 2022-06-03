package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class GetEventsByCountryDateOutput {

    private ArrayList<GetEventsByCountryDateData> data;
    private String success;

    public ArrayList<GetEventsByCountryDateData> getData() {

        return data;

    }

    public void setData(ArrayList<GetEventsByCountryDateData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
