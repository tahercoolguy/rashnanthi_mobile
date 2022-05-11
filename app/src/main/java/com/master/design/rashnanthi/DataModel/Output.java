package com.master.design.rashnanthi.DataModel;

import java.util.List;

public class Output{
	private List<DataItem> data;
	private String success;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(String success){
		this.success = success;
	}

	public String getSuccess(){
		return success;
	}
}