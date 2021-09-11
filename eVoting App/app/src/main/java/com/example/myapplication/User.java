package com.example.myapplication;

import java.util.Date;

public class User {


    private int constId;
    private String Name;
    private String dob;
    private String voterId;
    private String emailId;

    public User(int constId, String Name, String dob, String voterId, String emailId) {
        this.constId = constId;
        this.Name = Name;
        this.dob = dob;
        this.voterId = voterId;
        this.emailId = emailId;
    }

    public User()
    {}

    public int getConstId() {
        return constId;
    }

    public void setConstId(int constId) {
        this.constId = constId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
