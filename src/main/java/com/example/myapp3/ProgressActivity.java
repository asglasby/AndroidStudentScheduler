package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp3.Models.Course;

import java.util.ArrayList;

public class ProgressActivity extends OptionsMenuActivity {
    ProgressBar progressBar;
    private EditText classTotal;
    private EditText classComplete;
    private EditText classInProgress;
    private EditText classPlanned;
    private EditText classDropped;
    int counter = 95;
    int plannedCourses;
    int droppedCourses;
    int completedCourses;
    int inProgressCourses;
    public ArrayList<Course> allCoursesArrayList;
    DbHelper myDbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(ProgressActivity.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        countCourseData();
        progressTracker();
    }

    public void progressTracker(){
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(completedCourses);
    }

    public void countCourseData(){
        allCoursesArrayList = myDbConnection.readCourseRecords("SELECT * FROM course");

        classComplete = (EditText)findViewById(R.id.classCompleteTxt);
        classTotal = (EditText)findViewById(R.id.classTotalTxt);
        classInProgress = (EditText)findViewById(R.id.classInProgressTxt);
        classPlanned = (EditText)findViewById(R.id.classPlannedTxt);
        classDropped = (EditText)findViewById(R.id.classDroppedTxt);

        classTotal.setText(String.valueOf(allCoursesArrayList.size()));

        for(Course c: allCoursesArrayList){
            if(c.getStatus().equals("Planned")){
                plannedCourses++;
            }
            if(c.getStatus().equals("Dropped")){
                droppedCourses++;
            }
            if(c.getStatus().equals("In Progress")){
                inProgressCourses++;
            }
            if(c.getStatus().equals("Completed")){
                completedCourses++;
            }
        }


        classComplete.setText(String.valueOf(completedCourses));
        classInProgress.setText(String.valueOf(inProgressCourses));
        classPlanned.setText(String.valueOf(plannedCourses));
        classDropped.setText(String.valueOf(droppedCourses));
    }

    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();
    }

}
