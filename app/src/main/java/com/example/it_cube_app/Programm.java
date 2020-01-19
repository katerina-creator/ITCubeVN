package com.example.it_cube_app;

// Класс для объектов - образовательных программ
public class Programm {
    private int id;
    private String title;
    private String age;
    private String duration;
    private String description;
    private String logo;

    public Programm( ){
//        this.id = id;
//        this.title = title;
//        this.age = age;
//        this.duration = duration;
//        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() { return logo; }

    public void setLogo(String logo) { this.logo = logo; }
}
