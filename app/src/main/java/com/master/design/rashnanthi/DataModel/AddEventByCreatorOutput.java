package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class AddEventByCreatorOutput {
    private String success;
    private String message;
    private String eventid;

    public ArrayList<AddEventByCreatorOutputData> getEvedata() {
        return evedata;
    }

    public void setEvedata(ArrayList<AddEventByCreatorOutputData> evedata) {
        this.evedata = evedata;
    }

    private ArrayList<AddEventByCreatorOutputData> evedata = new ArrayList<AddEventByCreatorOutputData>();

    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getEventid() {
        return eventid;
    }
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }
}
