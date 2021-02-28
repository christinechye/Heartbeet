package com.example.livewell;

import android.net.Uri;

import com.google.firebase.firestore.FieldValue;

public class PostHelperClass {
    Uri imageUrl;
    String title, desc, currUid, create_time;

    public PostHelperClass() {

    }

    public PostHelperClass(Uri imageUrl, String title, String desc, String currUid, String create_time) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.desc = desc;
        this.currUid = currUid;
        this.create_time = create_time;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
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
