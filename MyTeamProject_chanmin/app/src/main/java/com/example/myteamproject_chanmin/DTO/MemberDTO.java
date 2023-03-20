package com.example.myteamproject_chanmin.DTO;

public class MemberDTO {
    String id , pw ,name , phone , email;

    public MemberDTO() { }

    public MemberDTO(String id, String name, String email , String phone ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;


    }

    public MemberDTO(String id, String pw, String name, String email, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.phone = phone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
