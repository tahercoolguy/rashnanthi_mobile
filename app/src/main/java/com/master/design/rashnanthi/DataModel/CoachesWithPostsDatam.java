package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class CoachesWithPostsDatam {
    private String id;
    private String eventdate;
    private String whatsapcountrycode;
    private String whatsapnumber;
    private String snapchat;
    private String instagram;
    private String website;
    private String payorfree;
    private String invoiceid;
    private String paymentstatus;
    private String eventtotal;
    private String postedby;
    private String creatorcoach;
    private String date;
    private String status;
    private String impcountries;
    private String image;
    private String imagetype;
    private ArrayList<CoachesWithPostsImageDatam> imagedata = new ArrayList<CoachesWithPostsImageDatam>();

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

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
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

    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

    public ArrayList<CoachesWithPostsImageDatam> getImagedata() {
        return imagedata;
    }

    public void setImagedata(ArrayList<CoachesWithPostsImageDatam> imagedata) {
        this.imagedata = imagedata;

    }
}
