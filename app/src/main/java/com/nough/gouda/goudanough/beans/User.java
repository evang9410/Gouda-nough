package com.nough.gouda.goudanough.beans;

import java.util.List;

/**
 * Created by 1333612 on 11/24/2016.
 */

public class User {

    private int id;
    private String name;
    private String userName;
    private String pass;
    private String email;
    private String postalCode;
    private List<Comment> comments;

    /**
     * Default Constructor
     */
    public User(){
        super();
    }

    public User(String name, String pass, String email, String postalCode, String userName) {
        this.name = name;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.postalCode = postalCode;

    }

    public int getId() {
        return id;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString()
    {
        String returnObj = "";
        returnObj += "user : {\n";
        returnObj += "id : " +id + ",\n";
        returnObj += "name : " +name + ",\n";
        returnObj += "pass : " +pass + ",\n";
        returnObj += "email : " +email + ",\n";
        returnObj += "postalCode : " +postalCode + "\n";
        returnObj += "}";
        return returnObj;
    }
}
