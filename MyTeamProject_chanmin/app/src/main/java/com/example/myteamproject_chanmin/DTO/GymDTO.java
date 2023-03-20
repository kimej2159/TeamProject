package com.example.myteamproject_chanmin.DTO;

public class GymDTO {

    String gym_id , gym_name ,  address , telephone_number , gym_picture;

    public  GymDTO ( ) {

    }


    public GymDTO(String gym_id, String gym_name, String address, String telephone_number, String gym_picture) {
        this.gym_id = gym_id;
        this.gym_name = gym_name;
        this.address = address;
        this.telephone_number = telephone_number;
        this.gym_picture = gym_picture;
    }

    public String getGym_id() {
        return gym_id;
    }

    public void setGym_id(String gym_id) {
        this.gym_id = gym_id;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getGym_picture() {
        return gym_picture;
    }

    public void setGym_picture(String gym_picture) {
        this.gym_picture = gym_picture;
    }
}
