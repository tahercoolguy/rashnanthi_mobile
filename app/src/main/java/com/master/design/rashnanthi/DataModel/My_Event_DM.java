package com.master.design.rashnanthi.DataModel;

public class My_Event_DM {


    private String textview;

    private int imageview1;

    private int imageview2;

    public My_Event_DM(String textview, int imageview1, int imageview2) {
        this.textview = textview;
        this.imageview1 = imageview1;
        this.imageview2 = imageview2;
    }

    public String getTextview() {
        return textview;
    }

    public void setTextview(String textview) {
        this.textview = textview;
    }

    public int getImageview1() {
        return imageview1;
    }

    public void setImageview1(int imageview1) {
        this.imageview1 = imageview1;
    }

    public int getImageview2() {
        return imageview2;
    }

    public void setImageview2(int imageview2) {
        this.imageview2 = imageview2;
    }
}
