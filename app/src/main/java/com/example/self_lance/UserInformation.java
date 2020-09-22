package com.example.self_lance;

import java.io.Serializable;


public class UserInformation implements Serializable {

    private String email, password;
    private String name, username,uid, lastName;


    public UserInformation() {

    }

    public UserInformation(String email, String password, String name, String uid, String lastName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.uid = uid;
        this.lastName = lastName;
        this.username= username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }

    public String getUid() {
        return uid;
    }

    public String getLastName() {
        return lastName;
    }


}
