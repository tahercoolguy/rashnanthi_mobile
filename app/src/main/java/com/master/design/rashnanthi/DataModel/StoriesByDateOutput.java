package com.master.design.rashnanthi.DataModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoriesByDateOutput {

    private ArrayList<StoriesByDateData> data;
    private String success;

    public ArrayList<AllStoryImage> allimagedata;

    public ArrayList<AllStoryImage> getAllimagedata() {
        return allimagedata;
    }

    public void setAllimagedata(ArrayList<AllStoryImage> allimagedata) {
        this.allimagedata = allimagedata;
    }

    public ArrayList<StoriesByDateData> getData() {

        return data;

    }

    public void setData(ArrayList<StoriesByDateData> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
