package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewAssessmentActivity extends AppCompatActivity {
    private EditText assessmentTitle;
    private EditText assessmentType;
    private EditText assessmentDate;
    private EditText courseId;
    DbHelper myDbConnection;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_assessment);
        //setContentView(R.layout.test_layout);
    }

    public void addAssessmentToDB(View view){
        myDbConnection = new DbHelper(AddNewAssessmentActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        EditText assessmentTitleEditText = (EditText)findViewById(R.id.assessment_title_txt);
        EditText assessmentTypeEditText = (EditText)findViewById(R.id.assessment_type_txt);
        EditText assessmentDateEditText = (EditText)findViewById(R.id.assessment_date_txt);
        EditText courseIdEditText = (EditText)findViewById(R.id.course_id_txt);

        String title = assessmentTitleEditText.getText().toString();
        String type = assessmentTypeEditText.getText().toString();
        String date = assessmentDateEditText.getText().toString();
        String courseId = courseIdEditText.getText().toString();

        String assessmentTitle = "assessmentTitle";
        String assessmentType = "assessmentType";
        String assessmentDate = "assessmentDate";
        String courseID = "courseId";

        myDbConnection.addAssessment(assessmentTitle, title, assessmentType, type, assessmentDate, date, courseID, courseId);

        myDbConnection.close();

        goBackToMain();
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
