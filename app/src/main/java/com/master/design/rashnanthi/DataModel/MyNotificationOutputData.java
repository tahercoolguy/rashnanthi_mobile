package com.master.design.rashnanthi.DataModel;

public class MyNotificationOutputData {

    private String id;
    private String notificationid;
    private String devicetoken;
    private String message;
    private String date;
    private String type;
    private String userid;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNotificationid() {
        return notificationid;
    }
    public void setNotificationid(String notificationid) {
        this.notificationid = notificationid;
    }
    public String getDevicetoken() {
        return devicetoken;
    }
    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
