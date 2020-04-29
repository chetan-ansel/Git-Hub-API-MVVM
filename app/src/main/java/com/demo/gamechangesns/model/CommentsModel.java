package com.demo.gamechangesns.model;

import com.google.gson.annotations.SerializedName;

public class CommentsModel {

    @SerializedName("body")
    private String mBody;

    public String getBody() {
        return mBody;
    }

    @SerializedName("user")
    private UserModel mUserModel;

    @SerializedName("id")
    private String mId;

    public UserModel getUserModel() {
        return mUserModel;
    }

    public String getId() {
        return mId;
    }
}
