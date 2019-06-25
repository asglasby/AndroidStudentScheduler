package com.example.myapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.myapp3.Models.Assessment;
import com.example.myapp3.Models.Course;
import com.example.myapp3.Models.Note;
import com.example.myapp3.Models.Term;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    //Specify Database Name and Version
    private static final String DATABASE_NAME = "progress.db";
    private static final int DATABASE_VERSION = 1;

    // Define DbConnection Constructor
    public DbHelper(Context context){

        // Call SqliteOpenHelper Constructor (Mandatory super call)
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTermTable(){
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS term (termID INTEGER PRIMARY KEY AUTOINCREMENT, termTitle TEXT NOT NULL UNIQUE, termStart DATE,termEnd DATE)");

    }

    public void dropTermTable(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS term ");
    }

    public void createCourseTable(){
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS course (courseID INTEGER PRIMARY KEY AUTOINCREMENT, courseTitle TEXT NOT NULL UNIQUE, startDate DATE, endDate DATE, status TEXT, mentorName TEXT, mentorPhone TEXT, mentorEmail TEXT, assessment TEXT, termID INTEGER)");
    }

    public void dropCourseTable(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS course ");
    }

    public void createAssessmentTable(){
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS assessment (assessmentID INTEGER PRIMARY KEY AUTOINCREMENT, assessmentTitle TEXT NOT NULL, assessmentType TEXT NOT NULL, assessmentDate DATE, courseID INTEGER)");
    }

    public void dropAssessmentTable(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS assessment");
    }

    public void insertRecord(String sqlStatement){
        this.getWritableDatabase().execSQL(sqlStatement);
    }

    public void createNoteTable(){
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS note (noteID INTEGER PRIMARY KEY AUTOINCREMENT, noteText TEXT NOT NULL, courseID INTEGER NOT NULL )");
    }

    public void dropNoteTable(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS note");
    }

    public void createMentorTable(){
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS mentor (mentorID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT NOT NULL, email TEXT NOT NULL)");
    }

    public void dropMentorTable(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS mentor");
    }



    public long addRecord(String termTitleKey, String termTitleValue, String termStartKey, String termStartValue, String termEndKey, String termEndValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(termTitleKey, termTitleValue);
        values.put(termStartKey, termStartValue);
        values.put(termEndKey, termEndValue);

        return db.insert("term", null, values);
    }

    public long updateTerm(String termIdKey, String termIdValue, String termTitleKey, String termTitleValue, String termStartKey, String termStartValue, String termEndKey, String termEndValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(termIdKey, termIdValue);
        values.put(termTitleKey, termTitleValue);
        values.put(termStartKey, termStartValue);
        values.put(termEndKey, termEndValue);

        return db.update("term", values, "termID = ?", new String[] {termIdValue});
    }

    public long addNote(String noteTitleKey, String noteTitleValue, String courseIdKey, String courseIdValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(noteTitleKey, noteTitleValue);
        values.put(courseIdKey, courseIdValue);

        return db.insert("note", null, values);
    }

    public long updateNote(String noteIdKey, String noteIdValue, String noteTitleKey, String noteTitleValue, String courseIdKey, String courseIdValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(noteIdKey, noteIdValue);
        values.put(noteTitleKey, noteTitleValue);
        values.put(courseIdKey, courseIdValue);

        return db.update("note", values, "noteID = ?", new String[] {noteTitleValue});
    }



    public long addCourse(String courseNameKey, String courseNameValue, String courseStartKey, String courseStartValue, String courseEndKey, String courseEndValue, String statusKey, String statusValue, String courseMentorKey, String courseMentorValue, String courseMentorPhoneKey, String courseMentorPhoneValue, String courseMentorEmailKey, String courseMentorEmailValue, String assessmentTypeKey, String assessmentTypeValue, String termIdKey, String termIdValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(courseNameKey, courseNameValue);
        values.put(courseStartKey, courseStartValue);
        values.put(courseEndKey, courseEndValue);
        values.put(statusKey, statusValue);
        values.put(courseMentorKey, courseMentorValue);
        values.put(courseMentorPhoneKey, courseMentorPhoneValue);
        values.put(courseMentorEmailKey, courseMentorEmailValue);
        values.put(assessmentTypeKey, assessmentTypeValue);
        values.put(termIdKey, termIdValue);

        return db.insert("course", null, values);
    }

    public long updateCourse(String courseIDKey, String courseIDValue, String courseNameKey, String courseNameValue, String courseStartKey, String courseStartValue, String courseEndKey, String courseEndValue, String statusKey, String statusValue, String courseMentorKey, String courseMentorValue, String courseMentorPhoneKey, String courseMentorPhoneValue, String courseMentorEmailKey, String courseMentorEmailValue, String assessmentTypeKey, String assessmentTypeValue, String termIdKey, String termIdValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(courseIDKey, courseIDValue);
        values.put(courseNameKey, courseNameValue);
        values.put(courseStartKey, courseStartValue);
        values.put(courseEndKey, courseEndValue);
        values.put(statusKey, statusValue);
        values.put(courseMentorKey, courseMentorValue);
        values.put(courseMentorPhoneKey, courseMentorPhoneValue);
        values.put(courseMentorEmailKey, courseMentorEmailValue);
        values.put(assessmentTypeKey, assessmentTypeValue);
        values.put(termIdKey, termIdValue);

        return db.update("course", values, "courseID = ?", new String[] {courseIDValue});
    }

    public long updateAssessment(String assessmentIDKey, String assessmentIDValue, String assessmentTitleKey, String assessmentTitleValue, String assessmentTypeKey, String assessmentTypeValue, String assessmentDateKey, String assessmentDateValue, String courseIDKey, String courseIDValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(assessmentIDKey, assessmentIDValue);
        values.put(assessmentTitleKey, assessmentTitleValue);
        values.put(assessmentTypeKey, assessmentTypeValue);
        values.put(assessmentDateKey, assessmentDateValue);
        values.put(courseIDKey, courseIDValue);

        return db.update("assessment", values, "assessmentID = ?", new String[] {assessmentIDValue});
    }

    public long addAssessment(String assessmentTitleKey, String assessmentTitleValue, String assessmentTypeKey, String assessmentTypeValue, String assessmentDateKey, String assessmentDateValue, String courseIDKey, String courseIDValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(assessmentTitleKey, assessmentTitleValue);
        values.put(assessmentTypeKey, assessmentTypeValue);
        values.put(assessmentDateKey, assessmentDateValue);
        values.put(courseIDKey, courseIDValue);

        return db.insert("assessment", null, values);
    }

    public void deleteRecord(String sqlStatement){
        this.getWritableDatabase().execSQL(sqlStatement);
    }

    public ArrayList<Term> readRecords(String sqlStatement){
        ArrayList<Term> allTerms = new ArrayList<Term>();
        int termID;
        String termTitle;
        String termStart;
        String termEnd;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement, null);

        while(cursor.moveToNext()){
            termID = cursor.getInt(cursor.getColumnIndex("termID"));
            termTitle = cursor.getString(cursor.getColumnIndex("termTitle"));
            termStart = cursor.getString(cursor.getColumnIndex("termStart"));
            termEnd = cursor.getString(cursor.getColumnIndex("termEnd"));

            allTerms.add(new Term(termID, termTitle, termStart, termEnd));
        }
        return allTerms;
    }

    public ArrayList<Course> readCourseRecords(String sqlStatement){
        ArrayList<Course> allCourses = new ArrayList<Course>();
        int courseID;
        String courseName;
        String courseStart;
        String courseEnd;
        String status;
        String mentorName;
        String mentorPhone;
        String mentorEmail;
        String assessmentType;
        int termID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement, null);

        while(cursor.moveToNext()){
            courseID = cursor.getInt(cursor.getColumnIndex("courseID"));
            courseName = cursor.getString(cursor.getColumnIndex("courseTitle"));
            courseStart = cursor.getString(cursor.getColumnIndex("startDate"));
            courseEnd = cursor.getString(cursor.getColumnIndex("endDate"));
            status = cursor.getString(cursor.getColumnIndex("status"));
            mentorName = cursor.getString(cursor.getColumnIndex("mentorName"));
            mentorPhone = cursor.getString(cursor.getColumnIndex("mentorPhone"));
            mentorEmail = cursor.getString(cursor.getColumnIndex("mentorEmail"));
            assessmentType = cursor.getString(cursor.getColumnIndex("assessment"));
            termID = cursor.getInt(cursor.getColumnIndex("termID"));

            allCourses.add(new Course(courseID, courseName, courseStart, courseEnd, status, mentorName, mentorPhone, mentorEmail, assessmentType, termID));
        }
        return allCourses;
    }

    public ArrayList<Assessment> readAssessmentRecords(String sqlStatement){
        ArrayList<Assessment> allAssessments = new ArrayList<Assessment>();
        int assessmentId;
        String assessmentTitle;
        String assessmentType;
        String assessmentDate;
        int courseId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement, null);

        while(cursor.moveToNext()){
            assessmentId = cursor.getInt(cursor.getColumnIndex("assessmentID"));
            assessmentTitle = cursor.getString(cursor.getColumnIndex("assessmentTitle"));
            assessmentType = cursor.getString(cursor.getColumnIndex("assessmentType"));
            assessmentDate = cursor.getString(cursor.getColumnIndex("assessmentDate"));
            courseId = cursor.getInt(cursor.getColumnIndex("courseID"));

            allAssessments.add(new Assessment(assessmentId, assessmentTitle, assessmentType, assessmentDate, courseId));

        }
        return allAssessments;
    }

    public ArrayList<Note> readNoteRecords(String sqlStatement){
        ArrayList<Note> allNotes = new ArrayList<>();
        int noteId;
        String noteText;
        int courseId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement, null);

        while(cursor.moveToNext()){
            noteId = cursor.getInt(cursor.getColumnIndex("noteID"));
            noteText = cursor.getString(cursor.getColumnIndex("noteText"));
            courseId = cursor.getInt(cursor.getColumnIndex("courseID"));

            allNotes.add(new Note(noteId, noteText, courseId));
        }
        return allNotes;
    }

    public ArrayList<Course> getCourseNames(String sqlStatement){
        ArrayList<Course> allCourses = new ArrayList<Course>();
        int courseID;
        String courseName;
        String courseStart;
        String courseEnd;
        String status;
        int mentorID;
        String assessmentType;
        int termID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement, null);

        while(cursor.moveToNext()){
            courseID = cursor.getInt(cursor.getColumnIndex("courseID"));
            courseName = cursor.getString(cursor.getColumnIndex("courseTitle"));
            courseStart = cursor.getString(cursor.getColumnIndex("startDate"));
            courseEnd = cursor.getString(cursor.getColumnIndex("endDate"));
            status = cursor.getString(cursor.getColumnIndex("status"));
            mentorID = cursor.getInt(cursor.getColumnIndex("mentorID"));
            assessmentType = cursor.getString(cursor.getColumnIndex("assessment"));
            termID = cursor.getInt(cursor.getColumnIndex("termID"));

            allCourses.add(new Course(courseName));
        }

        return allCourses;
    }

    public ArrayList<String> getAllCourses(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT * FROM course";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    String courseTitle = cursor.getString(cursor.getColumnIndex("courseTitle"));
                    list.add(courseTitle);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public ArrayList<String> getAllProvinces(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT * FROM provinces";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    String pname = cursor.getString(cursor.getColumnIndex("pname"));
                    list.add(pname);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }
}
