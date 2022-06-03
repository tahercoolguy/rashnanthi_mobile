package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class CoachesWithPostsOutput {
    private String success;
    private ArrayList<CoacheswithDatam> data = new ArrayList<CoacheswithDatam>();
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public ArrayList<CoacheswithDatam> getData() {
        return data;
    }
    public void setData(ArrayList<CoacheswithDatam> data) {
        this.data = data;
    }
}
