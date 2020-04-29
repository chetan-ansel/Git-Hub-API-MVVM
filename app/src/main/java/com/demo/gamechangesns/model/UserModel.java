package com.demo.gamechangesns.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("login")
    private String mUserName;

    public String getUserName() {
        return mUserName;
    }
}
