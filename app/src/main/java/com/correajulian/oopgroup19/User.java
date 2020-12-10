package com.correajulian.oopgroup19;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * creates a new user
     * @param user username
     * @param pass password
     */
    public User(String user, String pass) {
        username = user;
        password = pass;
    }
    /**
     * creates a new user
     * @param userinfo element 0 is username, element 1 is password, and element 3 is the user type
     */
    public User(String[] userinfo) {
        username = userinfo[0];
        password = userinfo[1];
        userType = Integer.parseInt(userinfo[2]);
    }
    /**
     * checks for valid login
     * @param user username
     * @param pass password
     * @return true if login matches
     */
    private boolean checkCredential(String user, String pass) {
        if (user.equals(this.username) && pass.equals(this.password)) {
            return true;
        }
        return false;
    }
    /**
     * @return usertype
     */
    public int getUserType() {
        return this.userType;
    }
    /**
     * creates a new user
     * @param user username
     * @param pass password
     * @param type 0 for customer and 1 for seller
     */
    private User(String user, String pass, int type) {
        this.username = user;
        this.password = pass;
        this.userType = type;
    }
    /**
     * creates a copy of a user
     * @param u existing user
     */
    protected User(User u) {
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.userType = u.getUserType();
    }
    /**
     * prints a user to console
     */
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
