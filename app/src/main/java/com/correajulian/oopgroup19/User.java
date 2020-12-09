package com.correajulian.oopgroup19;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Serializable {
    public User(String user, String pass) {
        username = user;
        password = pass;
    }
    public User(String[] userinfo) {
        username = userinfo[0];
        password = userinfo[1];
        userType = Integer.parseInt(userinfo[2]);
    }
    private boolean checkCredential(String user, String pass) {
        if (user.equals(this.username) && pass.equals(this.password)) {
            return true;
        }
        return false;
    }
    private int findUserType(String user, String pass) {
        //getuser type from the user String
        System.out.println("Not IMPLEMENTED YET");
        return 0;
    }

    public User handleLogin(String user, String pass) {
        if(checkCredential(user, pass)) {
            this.userType = findUserType(user, pass);
            User u = new User(user, pass, this.findUserType(user, pass));
            return u;
        }
        return null;
    }

    public int getUserType() {
        return this.userType;
    }

    private User(String user, String pass, int type) {
        this.username = user;
        this.password = pass;
        this.userType = type;
    }
    protected User(User u) {
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.userType = u.getUserType();
    }
    public void printUserInfo() {
        System.out.println(this.username);
        System.out.println(this.password);
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String toString (){
        return this.username + "," + this.password + "," + this.userType;
    }
    private String username;
    private String password;
    private int userType;
}
