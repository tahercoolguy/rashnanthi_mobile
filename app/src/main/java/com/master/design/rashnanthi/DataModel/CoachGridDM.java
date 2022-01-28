package com.master.design.rashnanthi.DataModel;

public class CoachGridDM {

    int Coach_Image;
    int Like_img;

    public CoachGridDM(int coach_Image, int like_img) {
        Coach_Image = coach_Image;
        Like_img = like_img;
    }

    public int getCoach_Image() {
        return Coach_Image;
    }

    public void setCoach_Image(int coach_Image) {
        Coach_Image = coach_Image;
    }

    public int getLike_img() {
        return Like_img;
    }

    public void setLike_img(int like_img) {
        Like_img = like_img;
    }

}
