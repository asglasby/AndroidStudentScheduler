package com.example.myapp3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp3.Models.Course;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditorCourseActivity extends AppCompatActivity {
    private EditText courseTitle;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText courseStatus;
    private EditText courseMentor;
    private EditText courseAssessment;
    private EditText termId;
    private String mode;
    private Course newCourse;
    private String courseIdIntent;
    private String courseTitleIntent;
    private String courseStartIntent;
    private String courseEndIntent;
    private String courseStatusIntent;
    private String courseMentorIntent;
    private String courseMentorPhoneIntent;
    private String courseMentorEmailIntent;
    private String courseAssessmentIntent;
    private String termIdIntent;
    private EditText courseTitleEditText;
    private EditText courseStartEditText;
    private EditText courseEndEditText;
    private EditText courseStatusEditText;
    private EditText courseMentorEditText;
    private EditText courseMentorPhoneEditText;
    private EditText courseMentorEmailEditText;
    private EditText courseAssessmentEditText;
    private EditText termIdEditText;
    private String courseId;
    private View view;
    Spinner statusSpinner;
    Spinner assessmentSpinner;
    int courseStatusPosition;
    int assessmentTypePosition;


    DbHelper myDbConnection;

    SQLiteDatabase sqLiteDatabase;
    Map<String, Integer> statusMap = new HashMap<>();
    Map<String, Integer> assessmentMap = new HashMap<>();

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";
    private static final String EXTRA_COURSE_NAME = "com.example.myapp3.course_name";
    private static final String EXTRA_COURSE_START = "com.example.myapp3.course_start";
    private static final String EXTRA_COURSE_END = "com.example.myapp3.course_end";
    private static final String EXTRA_COURSE_STATUS = "com.example.myapp3.course_status";
    private static final String EXTRA_COURSE_MENTOR_NAME = "com.example.myapp3.mentor_name";
    private static final String EXTRA_COURSE_MENTOR_PHONE = "com.example.myapp3.mentor_phone";
    private static final String EXTRA_COURSE_MENTOR_EMAIL = "com.example.myapp3.mentor_email";
    private static final String EXTRA_COURSE_ASSESSMENT_TYPE = "com.example.myapp3.course_assessment_type";
    private static final String EXTRA_TERM_ID = "com.example.myapp3.term_id";
    private EditText courseMentorPhone;
    private EditText courseMentorEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_course);

        newCourse = new Course();

        courseTitleEditText = (EditText)findViewById(R.id.courseTitle);
        courseStartEditText = (EditText)findViewById(R.id.courseStart);
        courseEndEditText = (EditText)findViewById(R.id.courseEnd);
       // courseStatusEditText = (EditText)findViewById(R.id.courseStatus);
      //  courseAssessmentEditText = (EditText)findViewById(R.id.courseAssessment);
        // termIdEditText = (EditText)findViewById(R.id.termId);
        courseMentorEditText = (EditText)findViewById(R.id.courseMentor);
        courseMentorPhoneEditText = (EditText)findViewById(R.id.courseMentorPhone);
        courseMentorEmailEditText = (EditText)findViewById(R.id.courseMentorEmail);

        courseTitle = (EditText)findViewById(R.id.courseTitle);
        courseStart = (EditText)findViewById(R.id.courseStart);
        courseEnd = (EditText)findViewById(R.id.courseEnd);
       // courseStatus = (EditText)findViewById(R.id.courseStatus);
       // courseAssessment = (EditText)findViewById(R.id.courseAssessment);
       // termId = (EditText)findViewById(R.id.termId);
        courseMentor = (EditText)findViewById(R.id.courseMentor);
        courseMentorPhone = (EditText)findViewById(R.id.courseMentorPhone);
        courseMentorEmail = (EditText)findViewById(R.id.courseMentorEmail);

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);
        courseTitleIntent = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        courseStartIntent = getIntent().getStringExtra(EXTRA_COURSE_START);
        courseEndIntent = getIntent().getStringExtra(EXTRA_COURSE_END);
        courseStatusIntent = getIntent().getStringExtra(EXTRA_COURSE_STATUS);
        courseAssessmentIntent = getIntent().getStringExtra(EXTRA_COURSE_ASSESSMENT_TYPE);
        courseMentorIntent = getIntent().getStringExtra(EXTRA_COURSE_MENTOR_NAME);
        courseMentorPhoneIntent = getIntent().getStringExtra(EXTRA_COURSE_MENTOR_PHONE);
        courseMentorEmailIntent = getIntent().getStringExtra(EXTRA_COURSE_MENTOR_EMAIL);
        termIdIntent = getIntent().getStringExtra(EXTRA_TERM_ID);

        courseTitleEditText.setText(courseTitleIntent);
        courseStartEditText.setText(courseStartIntent);
        courseEndEditText.setText(courseEndIntent);
        // courseStatusEditText.setText(courseStatusIntent);
        // courseAssessmentEditText.setText(courseAssessmentIntent);
        courseMentorEditText.setText(courseMentorIntent);
        courseMentorPhoneEditText.setText(courseMentorPhoneIntent);
        courseMentorEmailEditText.setText(courseMentorEmailIntent);
       // termIdEditText.setText(termIdIntent);

        statusSpinner = (Spinner)findViewById(R.id.courseStatus);
        assessmentSpinner = (Spinner)findViewById(R.id.courseAssessment);
        String [] statusArray = {"Planned", "Dropped", "In Progress", "Completed"};
        String [] assessmentArray = {"Objective", "Performance"};

        ArrayAdapter<String> statusArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statusArray );
        ArrayAdapter<String> assessmentArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assessmentArray );

        statusSpinner.setAdapter(statusArrayAdapter);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseStatusPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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


       // Map<String, Integer> statusMap = new HashMap<>();
        statusMap.put("Planned", 0);
        statusMap.put("Dropped", 1);
        statusMap.put("In Progress", 2);
        statusMap.put("Completed", 3);

        if(statusMap.containsKey(courseStatusIntent)){
            statusSpinner.setSelection(statusMap.get(courseStatusIntent));

        }else{
            statusSpinner.setSelection(3);
        }

        // Map<String, Integer> assessmentMap = new HashMap<>();
        assessmentMap.put("Objective", 0);
        assessmentMap.put("Performance",1);

        assessmentSpinner.setSelection(assessmentMap.get(courseAssessmentIntent));

        Button startOfClassAlarm = (Button)findViewById(R.id.startAlarmBtn);
        Button endOfClassAlarm = (Button)findViewById(R.id.startAlarmBtn);

        startOfClassAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = courseStartEditText.getText().toString();
                Calendar calendar = Calendar.getInstance();

                int year = Integer.parseInt(startDate.substring(0,4));
                int month = Integer.parseInt(startDate.substring(5,7));
                int day = Integer.parseInt(startDate.substring(8));
                Toast.makeText(EditorCourseActivity.this, "Date: " + year + " " +month +" "+ day, Toast.LENGTH_LONG).show();
                calendar.set(year, month - 1, day, 4,10,1);

                Intent intent = new Intent(EditorCourseActivity.this, AlarmReceiver.class);
                PendingIntent sender= PendingIntent.getBroadcast(EditorCourseActivity.this,0,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 1000, sender);
            }
        });

        endOfClassAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = courseStartEditText.getText().toString();
                Calendar calendar = Calendar.getInstance();

                int year = Integer.parseInt(startDate.substring(0,4));
                int month = Integer.parseInt(startDate.substring(5,7));
                int day = Integer.parseInt(startDate.substring(8));
                Toast.makeText(EditorCourseActivity.this, "Date: " + year + " " +month +" "+ day, Toast.LENGTH_LONG).show();
                calendar.set(year, month - 1, day, 4,10,1);

                Intent intent = new Intent(EditorCourseActivity.this, AlarmReceiver.class);
                PendingIntent sender= PendingIntent.getBroadcast(EditorCourseActivity.this,0,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 1000, sender);
            }
        });
    }

    public void deleteCourse(View view){
        if(EditorTermActivity.deleteFromTerm){
            updateCourse(view);
            goBackToTermList();

        }else{
            myDbConnection = new DbHelper(EditorCourseActivity.this);
            sqLiteDatabase = myDbConnection.getWritableDatabase();

            int intCourseId = Integer.parseInt(courseIdIntent);

            myDbConnection.deleteRecord("DELETE FROM course WHERE courseID = " + intCourseId);

            myDbConnection.close();

            goBackToMain();
        }
    }

    public void updateCourse(View view){
        myDbConnection = new DbHelper(EditorCourseActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        String title = courseTitleEditText.getText().toString();
        String start = courseStartEditText.getText().toString();
        String end = courseEndEditText.getText().toString();
        // String courseStatus = courseStatusEditText.getText().toString();
        String courseStatus;
        String courseMentor = courseMentorEditText.getText().toString();
        String courseMentorPhone = courseMentorPhoneEditText.getText().toString();
        String courseMentorEmail = courseMentorEmailEditText.getText().toString();
        String assessmentType;
        String courseTermId;

        if(EditorTermActivity.deleteFromTerm){
            courseTermId = "";
        }else{
            courseTermId = termIdIntent;
        }

        Map<Integer, String> statusIntMap = new HashMap<>();
        statusIntMap.put(0, "Planned");
        statusIntMap.put(1, "Dropped");
        statusIntMap.put(2, "In Progress");
        statusIntMap.put(3, "Completed");

        courseStatus = statusIntMap.get(courseStatusPosition);

        Map<Integer, String> assessmentIntMap = new HashMap<>();
        assessmentIntMap.put(0, "Objective");
        assessmentIntMap.put(1, "Performance");

        assessmentType = assessmentIntMap.get(assessmentTypePosition);

        String courseID = "courseID";
        String courseTitle = "courseTitle";
        String startDate = "startDate";
        String endDate = "endDate";
        String status = "status";
        String mentor = "mentorName";
        String mentorPhone = "mentorPhone";
        String mentorEmail = "mentorEmail";
        String assessment = "assessment";
        String termId = "termId";

        myDbConnection.updateCourse(courseID, courseIdIntent, courseTitle, title, startDate, start, endDate, end, status, courseStatus, mentor, courseMentor, mentorPhone, courseMentorPhone, mentorEmail, courseMentorEmail, assessment, assessmentType, termId, courseTermId );

        if(EditorTermActivity.deleteFromTerm) {
            EditorTermActivity deleteCourse = new EditorTermActivity();
            deleteCourse.availableCourseList = myDbConnection.readCourseRecords("SELECT * FROM course");
            myDbConnection.close();
            deleteCourse.deleteCourseFromTerm();
            goBackToTermList();
        }else{
            myDbConnection.close();
            goBackToMain();
        }
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, CourseList.class);
        startActivity(intent);
    }

    public void goBackToTermList(){
        Intent intent = new Intent(this, TermList.class);
        startActivity(intent);
    }

    public void openCourseNotes(View view){
        Intent intent = new Intent(this, NoteListActivity.class);
        intent.putExtra(EXTRA_COURSE_ID, courseIdIntent);
        startActivity(intent);
    }
}


