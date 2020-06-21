package com.example.schoolahapp;

import android.widget.EditText;

public class UserProfile {

    public String name;
    public String email;
    public String phone;

    public UserProfile() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserProfile(String username, String email, String PhoneNumber) {

        this.name = username;
        this.email = email;
        this.phone = PhoneNumber;

    }


}
