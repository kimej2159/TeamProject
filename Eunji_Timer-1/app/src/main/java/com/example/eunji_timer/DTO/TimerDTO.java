package com.example.eunji_timer.DTO;

public class TimerDTO {
    String id, pw, name, profile, title, timer;

    public TimerDTO(){ }

    public TimerDTO(String id, String pw, String name, String profile, String title, String timer) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.profile = profile;
        this.title = title;
        this.timer = timer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }
}
