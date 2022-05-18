package com.master.design.rashnanthi.DataModel;


import java.util.ArrayList;

public class SummaryForPaidEventOutput {

    private ArrayList<SummaryForPaidEventData> eventdata;
    private SummaryForPaidData data;
    private String success;

    public ArrayList<SummaryForPaidEventData> getEventdata() {

        return eventdata;

    }

    public void setEventdata(ArrayList<SummaryForPaidEventData> eventdata) {

        this.eventdata = eventdata;

    }

    public SummaryForPaidData getData() {

        return data;

    }

    public void setData(SummaryForPaidData data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
