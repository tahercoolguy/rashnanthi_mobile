package com.master.design.rashnanthi.DataModel;

import java.util.List;

public class OutputDM {
    
    private List<Data> data;
    
    private String success;

    public List<Data> getData() {

        
        return data;

    }

    public void setData(List<Data> data) {

        this.data = data;

    }

    public String getSuccess() {

        return success;

    }

    public void setSuccess(String success) {

        this.success = success;

    }
}
