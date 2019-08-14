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

import com.example.myapp3.Adapters.CourseAdapter;
import com.example.myapp3.Models.Course;

import java.util.ArrayList;

public class CourseList extends OptionsMenuActivity {

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";
    private static final String EXTRA_COURSE_NAME = "com.example.myapp3.course_name";
    private static final String EXTRA_COURSE_START = "com.example.myapp3.course_start";
    private static final String EXTRA_COURSE_END = "com.example.myapp3.course_end";
    private static final String EXTRA_COURSE_STATUS = "com.example.myapp3.course_status";
    private static final String EXTRA_COURSE_MENTOR_NAME = "com.example.myapp3.mentor_name";
    private static final String EXTRA_COURSE_MENTOR_PHONE = "com.example.myapp3.mentor_phone";
    private static final String EXTRA_COURSE_MENTOR_EMAIL = "com.example.myapp3.mentor_email";
  //  private static final String EXTRA_COURSE_ASSESSMENT_TYPE = "com.example.myapp3.course_assessment_type";
    private static final String EXTRA_TERM_ID = "com.example.myapp3.term_id";

    DbHelper myDbConnection;
    private static final String TAG = "CourseList";
    public static ArrayList<Course> allCoursesArrayList;
    ListView courseListListView;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(CourseList.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        courseListListView = (ListView)findViewById(R.id.CourseList);
        loadCourseData();

        // onclick listener to open course details.
        courseListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String courseName = allCoursesArrayList.get(position).getCourseName();
                String courseStart = allCoursesArrayList.get(position).getCourseStart();
                String courseEnd = allCoursesArrayList.get(position).getCourseEnd();
                String status = allCoursesArrayList.get(position).getStatus();
                String mentorName = allCoursesArrayList.get(position).getMentorName();
                String mentorPhone = allCoursesArrayList.get(position).getMentorPhone();
                String mentorEmail = allCoursesArrayList.get(position).getMentorEmail();
               // String assessmentType = allCoursesArrayList.get(position).getAssessmentType();
                int termIdFromArray = allCoursesArrayList.get(position).getTermID();
                int courseIdFromArray = allCoursesArrayList.get(position).getCourseID();
                String courseId = Integer.toString(courseIdFromArray);
                String termId = Integer.toString(termIdFromArray);

                Intent intent = new Intent(CourseList.this, EditorCourseActivity.class);

                intent.putExtra(EXTRA_ADD_UPDATE, "Add");
                intent.putExtra(EXTRA_COURSE_ID, courseId);
                intent.putExtra(EXTRA_COURSE_NAME, courseName);
                intent.putExtra(EXTRA_COURSE_START, courseStart);
                intent.putExtra(EXTRA_COURSE_END, courseEnd);
                intent.putExtra(EXTRA_COURSE_STATUS, status);
                intent.putExtra(EXTRA_COURSE_MENTOR_NAME, mentorName);
                intent.putExtra(EXTRA_COURSE_MENTOR_PHONE, mentorPhone);
                intent.putExtra(EXTRA_COURSE_MENTOR_EMAIL, mentorEmail);
                //intent.putExtra(EXTRA_COURSE_ASSESSMENT_TYPE, assessmentType);
                intent.putExtra(EXTRA_TERM_ID, termId);

                startActivity(intent);
            }
        });
    }

    public void openEditorForNewCourse(View view) {
        Intent intent = new Intent(this, AddNewCourseActivity.class);
        startActivity(intent);
    }

    public void loadCourseData(){
        allCoursesArrayList = myDbConnection.readCourseRecords("SELECT * FROM course");

        courseAdapter = new CourseAdapter(this, allCoursesArrayList);
        courseListListView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();
    }

}
