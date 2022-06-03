package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class CoacheswithDatam {
    private String id;
    private String creatorcoach;
    private String fullname;
    private String profilepic;
    private String email;
    private String password;
    private String secretcode;
    private String countryid;
    private String countrycode;
    private String whatscountrycode;
    private String mobile;
    private String whatsapnumber;
    private String status;
    private String auth;
    private String date;
    private String snapchat;
    private String instagram;
    private String deviceid;
    private String devicetype;
    private ArrayList<CoachesWithPostsDatam> postsdata = new ArrayList<CoachesWithPostsDatam>();
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCreatorcoach() {
        return creatorcoach;
    }
    public void setCreatorcoach(String creatorcoach) {
        this.creatorcoach = creatorcoach;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getProfilepic() {
        return profilepic;
    }
    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSecretcode() {
        return secretcode;
    }
    public void setSecretcode(String secretcode) {
        this.secretcode = secretcode;
    }
    public String getCountryid() {
        return countryid;
    }
    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }
    public String getCountrycode() {
        return countrycode;
    }
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
    public String getWhatscountrycode() {
        return whatscountrycode;
    }
    public void setWhatscountrycode(String whatscountrycode) {
        this.whatscountrycode = whatscountrycode;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getWhatsapnumber() {
        return whatsapnumber;
    }
    public void setWhatsapnumber(String whatsapnumber) {
        this.whatsapnumber = whatsapnumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAuth() {
        return auth;
    }
    public void setAuth(String auth) {
        this.auth = auth;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
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
    public String getDeviceid() {
        return deviceid;
    }
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
    public String getDevicetype() {
        return devicetype;
    }
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }
    public ArrayList<CoachesWithPostsDatam> getPostsdata() {
        return postsdata;
    }
    public void setPostsdata(ArrayList<CoachesWithPostsDatam> postsdata) {
        this.postsdata = postsdata;
    }
}
