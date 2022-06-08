package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class GetEventsByCountryDateOutput {

    private ArrayList<EventsDetailsData> data;
    private String success;

    public ArrayList<EventsDetailsData> getData() {

        return data;

    }

    public void setData(ArrayList<EventsDetailsData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
