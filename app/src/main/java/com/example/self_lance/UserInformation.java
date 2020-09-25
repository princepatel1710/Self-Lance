package com.example.self_lance;

import java.io.Serializable;


public class UserInformation implements Serializable {

    private String email, password;
    private String firstName, username,uid, lastName;


    public UserInformation() {

    }

    public UserInformation(String email, String password, String firstName, String uid, String lastName,String username) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
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

    public String getfirstName() {
        return firstName;
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
