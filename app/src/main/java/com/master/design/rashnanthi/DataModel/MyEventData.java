package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class MyEventData {
    private String id;
    private String eventdate;
    private String whatsapcountrycode;
    private String whatsapnumber;
    private String snapchat;
    private String instagram;
    private String website;
    private String payorfree;
    private String paymentstatus;
    private String eventtotal;
    private String postedby;
    private String creatorcoach;
    private String date;
    private String status;
    private String impcountries;
    private String image;
    private ArrayList<MyEventImageData> imagedata = new ArrayList<MyEventImageData>();
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEventdate() {
        return eventdate;
    }
    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }
    public String getWhatsapcountrycode() {
        return whatsapcountrycode;
    }
    public void setWhatsapcountrycode(String whatsapcountrycode) {
        this.whatsapcountrycode = whatsapcountrycode;
    }
    public String getWhatsapnumber() {
        return whatsapnumber;
    }
    public void setWhatsapnumber(String whatsapnumber) {
        this.whatsapnumber = whatsapnumber;
    }
    public String getSnapchat() {
        return snapchat;
    }
    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }
    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getPayorfree() {
        return payorfree;
    }
    public void setPayorfree(String payorfree) {
        this.payorfree = payorfree;
    }
    public String getPaymentstatus() {
        return paymentstatus;
    }
    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }
    public String getEventtotal() {
        return eventtotal;
    }
    public void setEventtotal(String eventtotal) {
        this.eventtotal = eventtotal;
    }
    public String getPostedby() {
        return postedby;
    }
    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }
    public String getCreatorcoach() {
        return creatorcoach;
    }
    public void setCreatorcoach(String creatorcoach) {
        this.creatorcoach = creatorcoach;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getImpcountries() {
        return impcountries;
    }
    public void setImpcountries(String impcountries) {
        this.impcountries = impcountries;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public ArrayList<MyEventImageData> getImagedata() {
        return imagedata;
    }
    public void setImagedata(ArrayList<MyEventImageData> imagedata) {
        this.imagedata = imagedata;
    }
}
