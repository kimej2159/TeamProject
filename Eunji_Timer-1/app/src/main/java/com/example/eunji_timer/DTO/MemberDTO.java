package com.example.eunji_timer.DTO;

public class MemberDTO {
    String id , pw ,name , phone , address, profile, title, timer;

    public MemberDTO(String id, String pw, String name, String phone, String address, String profile, String title, String timer) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.profile = profile;
        this.title = title;
        this.timer = timer;
    }

    public MemberDTO(String id, String name, String phone, String address, String profile, String title, String timer) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.profile = profile;
        this.title = title;
        this.timer = timer;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getTimer() { return timer; }

    public void setTimer(String timer) { this.timer = timer; }

}
