package com.nough.gouda.goudanough.beans;

/**
 * Created by 1333612 on 11/24/2016.
 */

public class User {

    private String name;
    private String pass;
    private String email;
    private String postalCode;

    public User(){
        super();
    }

    public User(String name, String pass, String email, String postalCode) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    @Override
    public String toString()
    {
        return "";
    }
}
