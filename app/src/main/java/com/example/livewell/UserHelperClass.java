package com.example.livewell;

public class UserHelperClass {
    String username, gender;

    public UserHelperClass() {

    }

    public UserHelperClass(String username, String gender) {
        this.username = username;
        this.gender = gender;
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
}
