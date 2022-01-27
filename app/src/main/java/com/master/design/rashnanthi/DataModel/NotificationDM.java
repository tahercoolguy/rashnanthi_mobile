package com.master.design.rashnanthi.DataModel;

import java.util.ArrayList;

public class NotificationDM {


    private String tittle;

    private String time;

    private String message;

    public NotificationDM(String tittle, String time, String message) {
        this.tittle = tittle;
        this.time = time;
        this.message = message;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
