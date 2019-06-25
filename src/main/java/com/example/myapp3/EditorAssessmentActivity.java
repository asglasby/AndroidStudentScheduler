package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.myapp3.Models.Assessment;

public class EditorAssessmentActivity extends AppCompatActivity {
    private EditText assessmentTitle;
    private EditText assessmentType;
    private EditText assessmentDate;
    private EditText courseId;
    private String mode;
    private Assessment newAssessment;
    private String assessmentIdIntent;
    private String assessmentTitleIntent;
    private String assessmentTypeIntent;
    private String assessmentDateIntent;
    private String courseIdIntent;
    private Assessment oldAssessment;
    private String oldAssessmentTitle;
    private String oldAssessmentType;
    private String oldAssessmentDate;
    private String oldcourseId;
    private EditText assessmentTitleEditText;
    private EditText assessmentTypeEditText;
    private EditText assessmentDateEditText;
    private EditText courseIdEditText;
    private String assessmentId;

    DbHelper myDbConnection;

    SQLiteDatabase sqLiteDatabase;

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_ASSESSMENT_ID = "com.example.myapp3.assessment_id";
    private static final String EXTRA_ASSESSMENT_TITLE = "com.example.myapp3.assessment_title";
    private static final String EXTRA_ASSESSMENT_TYPE = "com.example.myapp3.assessment_type";
    private static final String EXTRA_ASSESSMENT_DATE = "com.example.myapp3.assessment_date";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_assessment);

        newAssessment = new Assessment();
        oldAssessment = new Assessment();

        assessmentTitleEditText = (EditText)findViewById(R.id.assessment_title_txt);
        assessmentTypeEditText = (EditText)findViewById(R.id.assessment_type_txt);
        assessmentDateEditText = (EditText)findViewById(R.id.assessment_date_txt);
        courseIdEditText = (EditText)findViewById(R.id.course_id_txt);

        assessmentTitle = (EditText)findViewById(R.id.assessment_title_txt);
        assessmentType = (EditText)findViewById(R.id.assessment_type_txt);
        assessmentDate = (EditText)findViewById(R.id.assessment_date_txt);
        courseId = (EditText)findViewById(R.id.course_id_txt);

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        assessmentIdIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_ID);
        assessmentTitleIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_TITLE);
        assessmentTypeIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_TYPE);
        assessmentDateIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_DATE);
        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

        assessmentTitleEditText.setText(assessmentTitleIntent);
        assessmentTypeEditText.setText(assessmentTypeIntent);
        assessmentDateEditText.setText(assessmentDateIntent);
        courseIdEditText.setText(courseIdIntent);


    }

    public void deleteAssessment(View View){
        myDbConnection = new DbHelper(EditorAssessmentActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        int intAssessmentId = Integer.parseInt(assessmentIdIntent);

        myDbConnection.deleteRecord("DELETE FROM assessment WHERE assessmentID = " + intAssessmentId);

        myDbConnection.close();

        goBackToMain();
    }

    public void updateAssessment(View view){
        myDbConnection = new DbHelper(EditorAssessmentActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        String title = assessmentTitleEditText.getText().toString();
        String type = assessmentTitleEditText.getText().toString();
        String date = assessmentDateEditText.getText().toString();
        String courseAssessmentId = courseIdEditText.getText().toString();

        String assessmentID = "assessmentID";
        String assessmentTitle = "assessmentTitle";
        String assessmentType = "assessmentType";
        String assessmentDate = "assessmentDate";
        String courseId = "courseId";

        myDbConnection.updateAssessment(assessmentID, assessmentIdIntent, assessmentTitle, title, assessmentType, type, assessmentDate, date, courseId, courseAssessmentId);

        myDbConnection.close();

        goBackToMain();
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, AssessmentList.class);

        startActivity(intent);
    }
}
