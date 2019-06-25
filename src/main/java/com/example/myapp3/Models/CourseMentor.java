package com.example.myapp3.Models;

public class CourseMentor {
    private int courseMentorID;
    private int courseID;
    private int mentorID;

    public CourseMentor(int courseMentorID, int courseID, int mentorID) {
        this.courseMentorID = courseMentorID;
        this.courseID = courseID;
        this.mentorID = mentorID;
    }

    public CourseMentor(){

    }

    public int getCourseMentorID() {
        return courseMentorID;
    }

    public void setCourseMentorID(int courseMentorID) {
        this.courseMentorID = courseMentorID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }
}
