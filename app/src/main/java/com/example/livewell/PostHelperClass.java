package com.example.livewell;

import android.net.Uri;

import com.google.firebase.firestore.FieldValue;

public class PostHelperClass {
    String imageUrl, username, title, desc, currUid, create_time;

    public PostHelperClass() {

    }

    public PostHelperClass(String imageUrl, String username, String title, String desc, String currUid, String create_time) {
        this.imageUrl = imageUrl;
        this.username = username;
        this.title = title;
        this.desc = desc;
        this.currUid = currUid;
        this.create_time = create_time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrUid() {
        return currUid;
    }

    public void setCurrUid(String currUid) {
        this.currUid = currUid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
