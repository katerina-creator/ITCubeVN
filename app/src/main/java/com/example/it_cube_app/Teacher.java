package com.example.it_cube_app;

import android.media.Image;
import android.widget.ImageView;

public class Teacher {
    int _id;
    String fullname;
    String phone;
    String education;
    String progress;
    Image photo;


    public Teacher(){
    }

    public Teacher(int id, String fullname, String phone, Image photo){
        this._id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.education = education;
        this.progress = progress;
        this.photo = photo;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getEducation(){
        return this.education;
    }

    public void setEducation(String education){
        this.education = education;
    }

    public String getProgress(){
        return this.progress;
    }

    public void setProgress(String progress){
        this.progress = progress;
    }
}
