package com.example.self_lance;

public class Constant {
    private static UserInformation info;


    public static UserInformation getInfo() {
        return info;
    }

    public static void setInfo(UserInformation info) {
        Constant.info = info;
    }
}
