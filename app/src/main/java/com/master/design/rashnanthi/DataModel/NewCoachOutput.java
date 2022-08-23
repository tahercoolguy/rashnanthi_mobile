package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class NewCoachOutput {
    private ArrayList<NewCoachData> data;

    private String success;

    public ArrayList<NewCoachData> getData ()
    {
        return data;
    }

    public void setData (ArrayList<NewCoachData> data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

}
