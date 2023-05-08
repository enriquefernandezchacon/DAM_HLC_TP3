package com.example.examenretrofitbase.modelo;

import com.google.gson.annotations.SerializedName;

public class NewPost {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("userId")
    private String userId;

    public NewPost(String title, String body, String userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUserId() {
        return userId;
    }
}
