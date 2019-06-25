package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewCourseActivity extends AppCompatActivity {
    private EditText courseTitle;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText courseStatus;
    private EditText courseMentor;
    private EditText courseAssessment;
    DbHelper myDbConnection;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
    }

    public void addCourseToDB(View view){
        myDbConnection = new DbHelper(AddNewCourseActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        EditText courseTitleEditText = (EditText)findViewById(R.id.courseTitle);
        EditText courseStartEditText = (EditText)findViewById(R.id.courseStart);
        EditText courseEndEditText = (EditText)findViewById(R.id.courseEnd);
        //EditText courseStatusEditText = (EditText)findViewById(R.id.courseStatus);
        EditText courseMentorEditText = (EditText)findViewById(R.id.courseMentor);
       // EditText courseAssessmentEditText = (EditText)findViewById(R.id.courseAssessment);
       // EditText courseTermIdEditText = (EditText)findViewById(R.id.termId);

        String title = courseTitleEditText.getText().toString();
        String start = courseStartEditText.getText().toString();
        String end = courseEndEditText.getText().toString();
        //String courseStatus = courseStatusEditText.getText().toString();
        String courseMentor = courseMentorEditText.getText().toString();
        // int intMentor = Integer.parseInt(courseMentor);
       // String assessmentType = courseAssessmentEditText.getText().toString();
       // String courseTermId = courseTermIdEditText.getText().toString();
        // int intTermID = Integer.parseInt(courseTermId);

        String courseTitle = "courseTitle";
        String startDate = "startDate";
        String endDate = "endDate";
        String status = "status";
        String mentorID = "mentorID";
        String assessment = "assessment";
        String termId = "termId";

        // myDbConnection.addCourse(courseTitle, title, startDate, start, endDate, end, status, courseStatus, mentorID, courseMentor, assessment, assessmentType, termId, courseTermId );


        myDbConnection.close();

        goBackToMain();

    }

    public void goBackToMain(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
