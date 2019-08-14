package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddNewAssessmentActivity extends OptionsMenuActivity {
    private String courseIdIntent;
    String courseId;
    private EditText assessmentTitle;
    private EditText assessmentType;
    private EditText assessmentDate;
    DbHelper myDbConnection;
    SQLiteDatabase sqLiteDatabase;
    Spinner assessmentSpinner;
    int assessmentTypePosition;
    Map<String, Integer> assessmentMap = new HashMap<>();

    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_assessment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDbConnection = new DbHelper(AddNewAssessmentActivity.this);

        myDbConnection.getWritableDatabase();

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

        assessmentSpinner = (Spinner)findViewById(R.id.assessmentType);

        String [] assessmentArray = {"Objective", "Performance"};

        ArrayAdapter<String> assessmentArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assessmentArray );

        assessmentSpinner.setAdapter(assessmentArrayAdapter);
        assessmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assessmentTypePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        assessmentMap.put("Objective", 0);
        assessmentMap.put("Performance",1);
    }

    public void addAssessmentToDB(View view){

        EditText assessmentTitleEditText = (EditText)findViewById(R.id.assessment_title_txt);

        EditText assessmentDateEditText = (EditText)findViewById(R.id.assessment_date_txt);


        String title = assessmentTitleEditText.getText().toString();

        String type;
        String date = assessmentDateEditText.getText().toString();

        String assessmentTitle = "assessmentTitle";
        String assessmentType = "assessmentType";
        String assessmentDate = "assessmentDate";
        String courseID = "courseId";

        Map<Integer, String> assessmentIntMap = new HashMap<>();
        assessmentIntMap.put(0, "Objective");
        assessmentIntMap.put(1, "Performance");

        type = assessmentIntMap.get(assessmentTypePosition);

        myDbConnection.addAssessment(assessmentTitle, title, assessmentType, type, assessmentDate, date, courseID, courseIdIntent);

        goBackToMain();
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, CourseList.class);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        myDbConnection.close();
    }
}
