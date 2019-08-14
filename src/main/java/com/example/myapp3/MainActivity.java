package com.example.myapp3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends OptionsMenuActivity {

    DbHelper myDbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(MainActivity.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        //Create Tables
        myDbConnection.createTermTable();
        myDbConnection.createCourseTable();
        myDbConnection.createAssessmentTable();
        myDbConnection.createNoteTable();
        myDbConnection.createAssessmentNoteTable();
    }


    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();
        Toast.makeText(MainActivity.this, myDbConnection.getDatabaseName() + "Closed", Toast.LENGTH_SHORT).show();
    }

    public void openTermListView(View view) {
        Intent intent = new Intent(this, TermList.class);
        startActivity(intent);
    }

    public void openTermListDetailsView(View view) {
        Intent intent = new Intent(this, TermDetail.class);
        startActivity(intent);
    }

    public void openCourseListView(View view) {
        Intent intent = new Intent(this, CourseList.class);
        startActivity(intent);
    }

    public void openCourseDetailListView(View view) {
        Intent intent = new Intent(this, CourseDetail.class);
        startActivity(intent);
    }

    public void openAssessmentListView(View view) {
        Intent intent = new Intent(this, AssessmentList.class);
        startActivity(intent);
    }

    public void openAssessmentDetailListView(View view) {
        Intent intent = new Intent(this, AssessmentDetail.class);
        startActivity(intent);
    }

    public void openProgressView(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }
}
