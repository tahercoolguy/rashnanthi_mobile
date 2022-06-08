package com.master.design.rashnanthi.DataModel;


import java.util.ArrayList;

public class SummaryForPaidEventOutput {

    private String success;
    private SummaryForPaidData data;
    private ArrayList<SummaryForPaidEventData> eventdata = new ArrayList<SummaryForPaidEventData>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public SummaryForPaidData getData() {
        return data;
    }
    public void setData(SummaryForPaidData data) {
        this.data = data;
    }
    public ArrayList<SummaryForPaidEventData> getEventdata() {
        return eventdata;
    }
    public void setEventdata(ArrayList<SummaryForPaidEventData> eventdata) {
        this.eventdata = eventdata;
    }
}
