package com.demo.gamechangesns.model;

import com.google.gson.annotations.SerializedName;

public class IssueModel {

    @SerializedName("body")
    private String mBody;
    @SerializedName("number")
    private String mNumber;
    @SerializedName("comments_url")
    private String mCommentsUrl;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("id")
    private String mId;

    public String getBody() {
        return mBody;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }
}
