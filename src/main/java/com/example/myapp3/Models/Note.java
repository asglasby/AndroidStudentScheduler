package com.example.myapp3.Models;

public class Note {
    private int noteID;
    private String noteText;
    private int courseID;

    public Note(int noteID, String noteText, int courseID) {
        this.noteID = noteID;
        this.noteText = noteText;
        this.courseID = courseID;
    }

    public Note(){

    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

}
