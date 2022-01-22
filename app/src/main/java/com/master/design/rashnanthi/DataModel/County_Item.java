package com.master.design.rashnanthi.DataModel;

public class County_Item {
    private  String mcountryName;
    private  int mcountryImage;

    public County_Item(String mcountryName, int mcountryImage) {
        this.mcountryName = mcountryName;
        this.mcountryImage = mcountryImage;
    }

    public String getMcountryName() {
        return mcountryName;
    }

    public void setMcountryName(String mcountryName) {
        this.mcountryName = mcountryName;
    }

    public int getMcountryImage() {
        return mcountryImage;
    }

    public void setMcountryImage(int mcountryImage) {
        this.mcountryImage = mcountryImage;
    }
}
