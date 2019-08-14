package com.example.myapp3.Models;

public class AssessmentNote {
    private int noteID;
    private String noteText;
    private int assessmentID;

    public AssessmentNote(int noteID, String noteText, int assessmentID) {
        this.noteID = noteID;
        this.noteText = noteText;
        this.assessmentID = assessmentID;
    }

    public AssessmentNote(){

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

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
}
