package com.example.myapp3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

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

        // Display Database Name
        Toast.makeText(MainActivity.this, myDbConnection.getDatabaseName(), Toast.LENGTH_SHORT).show();


       // myDbConnection.dropCourseTable();
       // myDbConnection.createCourseTable();


       // myDbConnection.insertRecord("INSERT INTO mentor(name, phone, email) VALUES('Reggie Doe', '123-456-7890', 'reggie@wgu.edu')");
      // myDbConnection.insertRecord("INSERT INTO note(noteText, courseID) VALUES('This is a math note', 1)");
       //myDbConnection.insertRecord("INSERT INTO note(noteText, courseID) VALUES('This is a math note', 1)");
      // myDbConnection.insertRecord("INSERT INTO note(noteText, courseID) VALUES('This is a math note', 1)");

       // myDbConnection.insertRecord("INSERT INTO assessment(assessmentTitle, assessmentType, assessmentDate, courseID) VALUES('Assess1', 'Objective', '2019-01-01', 5)");

        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Critical Thinking', '2019-01-01', '2019-01-31', 'planned', 'John Doe', '123-456-7896', 'john@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Math', '2019-01-01', '2019-01-31', 'planned', 'Jane Doe', '123-456-7895', 'jane@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Spelling', '2019-01-01', '2019-01-31', 'planned', 'Jo Doe', '123-456-7897', 'jo@doe.com', 'Performance', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Algebra', '2019-01-01', '2019-01-31', 'planned', 'John Doe', '123-456-7896', 'john@doe.com', 'Objective', NULL)");
        //  myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('History', '2019-01-01', '2019-01-31', 'planned', 'Jane Doe', '123-456-7895', 'jane@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Physics', '2019-01-01', '2019-01-31', 'planned', 'Jo Doe', '123-456-7897', 'jo@doe.com', 'Performance', NULL)");

        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Java I', '2019-01-01', '2019-01-31', 'planned', 'John Doe', '123-456-7896', 'john@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Java II', '2019-01-01', '2019-01-31', 'planned', 'Jane Doe', '123-456-7895', 'jane@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Javascript I', '2019-01-01', '2019-01-31', 'planned', 'Jo Doe', '123-456-7897', 'jo@doe.com', 'Performance', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Python', '2019-01-01', '2019-01-31', 'planned', 'John Doe', '123-456-7896', 'john@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Web Design', '2019-01-01', '2019-01-31', 'planned', 'Jane Doe', '123-456-7895', 'jane@doe.com', 'Objective', NULL)");
        // myDbConnection.insertRecord("INSERT INTO course(courseTitle, startDate, endDate, status, mentorName, mentorPhone, mentorEmail, assessment, termID) VALUES('Project Management', '2019-01-01', '2019-01-31', 'planned', 'Jo Doe', '123-456-7897', 'jo@doe.com', 'Performance', NULL)");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.homeItem:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.termItem:
                intent = new Intent(this, TermList.class);
                startActivity(intent);
                return true;

            case R.id.courseItem:
                intent = new Intent(this, CourseList.class);
                startActivity(intent);
                return true;

            case R.id.assessmentItem:
                intent = new Intent(this, AssessmentList.class);
                startActivity(intent);
                return true;

            case R.id.subTermItem:
                intent = new Intent(this, TermList.class);
                startActivity(intent);
                return true;

            case R.id.subCourseItem:
                intent = new Intent(this, CourseList.class);
                startActivity(intent);
                return true;

            case R.id.subAssessmentItem:
                intent = new Intent(this, AssessmentList.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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

    public void openCourseListView(View view) {
        Intent intent = new Intent(this, CourseList.class);
        startActivity(intent);
    }

    public void openAssessmentListView(View view) {
        Intent intent = new Intent(this, AssessmentList.class);
        startActivity(intent);
    }
}
