package com.example.myapp3.Models;

public class Mentor {
    private int mentorID;
    private String name;
    private String email;
    private String phone;

    public Mentor(int mentorID, String name, String email, String phone) {
        this.mentorID = mentorID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Mentor() {

    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
