package com.example.livewell;

public class BlogPost {

    public String username, create_time, currUid, imageUrl, title, desc;

    public BlogPost() { }

    public BlogPost(String username, String create_time, String currUid, String imageUrl, String title, String desc) {
        this.username = username;
        this.create_time = create_time;
        this.currUid = currUid;
        this.imageUrl = imageUrl;
        this.title = title;
        this.desc = desc;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getCreate_time() { return create_time; }

    public void setCreate_time(String create_time) { this.create_time = create_time; }

    public String getCurrUid() {
        return currUid;
    }

    public void setCurrUid(String currUid) {
        this.currUid = currUid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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
}
