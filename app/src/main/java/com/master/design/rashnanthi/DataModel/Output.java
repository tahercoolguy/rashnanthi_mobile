package com.master.design.rashnanthi.DataModel;

import java.util.List;

public class Output{
	private List<DataItem> data;
	private int success;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}
}