package com.example.livewell;

import java.util.ArrayList;
import java.util.List;

public class UserHelperClass {
    String username, gender;
    List<String> postsID = new ArrayList<>();

    public UserHelperClass() {

    }

    public UserHelperClass(String username, String gender, List<String> postsID) {
        this.username = username;
        this.gender = gender;
        this.postsID = postsID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getPostsID() {
        return postsID;
    }

    public void setPostsID(List<String> postsID) {
        this.postsID = postsID;
    }
}
