package com.example.self_lance;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;


public class UserInformation implements Parcelable {

    private String Exeperience, Description;
    private String email, password;
    private String firstName, username, uid, lastName;
    private List<Integer> skillList;
    private String price;

    public UserInformation() {
    }

    public UserInformation(String firstName, String lastName, String uid, String password, String email, String username) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.uid = uid;
        this.lastName = lastName;
        this.username = username;
    }

    public UserInformation(String firstName, String lastName, String uid, String password, String email,
                           String username, String Exeperience, String Description, List<Integer> skillList, String price) {
        this.firstName = firstName;
        this.uid = uid;
        this.lastName = lastName;
        this.username = username;
        this.username = username;
        this.email = email;
        this.password = password;
        this.Exeperience = Exeperience;
        this.Description = Description;
        this.skillList = skillList;
        this.price = price;
    }

    protected UserInformation(Parcel in) {
        Exeperience = in.readString();
        Description = in.readString();
        email = in.readString();
        password = in.readString();
        firstName = in.readString();
        username = in.readString();
        uid = in.readString();
        lastName = in.readString();
        price = in.readString();
    }

    public static final Creator<UserInformation> CREATOR = new Creator<UserInformation>() {
        @Override
        public UserInformation createFromParcel(Parcel in) {
            return new UserInformation(in);
        }

        @Override
        public UserInformation[] newArray(int size) {
            return new UserInformation[size];
        }
    };

    public String getExeperience() {
        return Exeperience;
    }

    public void setExeperience(String exeperience) {
        Exeperience = exeperience;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Integer> skillList) {
        this.skillList = skillList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Exeperience);
        parcel.writeString(Description);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(firstName);
        parcel.writeString(username);
        parcel.writeString(uid);
        parcel.writeString(lastName);
        parcel.writeString(price);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}