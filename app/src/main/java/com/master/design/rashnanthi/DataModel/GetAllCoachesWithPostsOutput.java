package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class GetAllCoachesWithPostsOutput {
    private ArrayList<GetAllCoachesWithPostsOutputData> data;
    private String success;

    public ArrayList<GetAllCoachesWithPostsOutputData> getData() {

        return data;

    }

    public void setData(ArrayList<GetAllCoachesWithPostsOutputData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
