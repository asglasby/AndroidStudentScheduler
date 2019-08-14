package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp3.Adapters.AssessmentAdapter;
import com.example.myapp3.Models.Assessment;

import java.util.ArrayList;


public class AssessmentList extends OptionsMenuActivity {

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
    AssessmentAdapter assessmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(AssessmentList.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        assessmentListListView = (ListView)findViewById(R.id.AssessmentList);
        loadAssessmentData();

        // onclick listener to open course details.
        assessmentListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int assessmentIdFromArray = allAssessmentsArrayList.get(position).getAssessmentID();
                String assessmentTitle = allAssessmentsArrayList.get(position).getAssessmentTitle();
                String assessmentType = allAssessmentsArrayList.get(position).getAssessmentType();
                String assessmentDate = allAssessmentsArrayList.get(position).getAssessmentDate();
                int courseIdFromArray = allAssessmentsArrayList.get(position).getCourseID();
                String courseId = Integer.toString(courseIdFromArray);
                String assessmentId = Integer.toString(assessmentIdFromArray);

                Intent intent = new Intent(AssessmentList.this, EditorAssessmentActivity.class);

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
        startActivity(intent);
    }

    public void loadAssessmentData(){
        allAssessmentsArrayList = myDbConnection.readAssessmentRecords("SELECT * FROM assessment");

        assessmentAdapter = new AssessmentAdapter(this, allAssessmentsArrayList);
        assessmentListListView.setAdapter(assessmentAdapter);
        assessmentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause(){
        super.onPause();
        myDbConnection.close();
    }

}
