package com.example.uts_pbp;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String email;
    @SerializedName("password")
    private String password;

    public User(String username, String password){
        this.email = username;
        this.password = password;
    }

    public String getUsername(){ return email;}

    public void setUsername(String email) {this.email = email;}

    public String getPassword(){ return password;}

    public void setPassword(String password) {this.password = password;}

}
