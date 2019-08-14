package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp3.Adapters.AssessmentAdapter;
import com.example.myapp3.Adapters.AssessmentDetailAdapter;
import com.example.myapp3.Models.Assessment;
import com.example.myapp3.Models.Course;

import java.util.ArrayList;

public class CourseAssessmentActivity extends OptionsMenuActivity {
    private String courseIdIntent;
    String courseId;
    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_ASSESSMENT_ID = "com.example.myapp3.assessment_id";
    private static final String EXTRA_ASSESSMENT_TITLE = "com.example.myapp3.assessment_title";
    private static final String EXTRA_ASSESSMENT_TYPE = "com.example.myapp3.assessment_type";
    private static final String EXTRA_ASSESSMENT_DATE = "com.example.myapp3.assessment_date";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    DbHelper myDbConnection;

    private static final String TAG = "AssessmentList";
    public static ArrayList<Assessment> allAssessmentsArrayList;
    ListView assessmentListListView;
    AssessmentDetailAdapter assessmentAdapter;
    public ArrayList<Assessment> courseAssessmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_assessment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(CourseAssessmentActivity.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

        assessmentListListView = (ListView)findViewById(R.id.AssessmentList);
        loadAssessmentData();

        // onclick listener to open course details.
        assessmentListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int assessmentIdFromArray = courseAssessmentList.get(position).getAssessmentID();
                String assessmentTitle = courseAssessmentList.get(position).getAssessmentTitle();
                String assessmentType = courseAssessmentList.get(position).getAssessmentType();
                String assessmentDate = courseAssessmentList.get(position).getAssessmentDate();
                int courseIdFromArray = courseAssessmentList.get(position).getCourseID();
                courseId = Integer.toString(courseIdFromArray);
                String assessmentId = Integer.toString(assessmentIdFromArray);

                Intent intent = new Intent(CourseAssessmentActivity.this, EditorAssessmentActivity.class);

                intent.putExtra(EXTRA_ADD_UPDATE, "Add");
                intent.putExtra(EXTRA_ASSESSMENT_ID, assessmentId);
                intent.putExtra(EXTRA_ASSESSMENT_TITLE, assessmentTitle);
                intent.putExtra(EXTRA_ASSESSMENT_TYPE, assessmentType);
                intent.putExtra(EXTRA_ASSESSMENT_DATE, assessmentDate);
                intent.putExtra(EXTRA_COURSE_ID, courseId);

                startActivity(intent);

            }
        });
    }

    public void openEditorForNewAssessment(View view) {
        Intent intent = new Intent(this, AddNewAssessmentActivity.class);
        intent.putExtra(EXTRA_COURSE_ID, courseIdIntent);
        startActivity(intent);
    }

    public void loadAssessmentData(){
        allAssessmentsArrayList = myDbConnection.readAssessmentRecords("SELECT * FROM assessment");

        for(Assessment a: allAssessmentsArrayList){
            if(a.getCourseID() == Integer.parseInt(courseIdIntent)){
                courseAssessmentList.add(a);
            }
        }

        assessmentAdapter = new AssessmentDetailAdapter(this, courseAssessmentList);
        assessmentListListView.setAdapter(assessmentAdapter);
        assessmentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause(){
        super.onPause();
        myDbConnection.close();
    }

}
