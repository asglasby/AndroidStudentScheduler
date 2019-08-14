package com.example.myapp3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp3.Models.Course;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditorCourseActivity extends OptionsMenuActivity implements DatePickerDialog.OnDateSetListener {
    private EditText courseTitle;
   // private EditText courseStart;
   // private EditText courseEnd;
    private TextView courseStart;
    private TextView courseEnd;
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
   // private EditText courseStartEditText;
    private TextView courseStartTextView;
    private TextView courseEndTextView;
    //private EditText courseEndEditText;
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
    int datePickerNum = 0;

    DbHelper myDbConnection;


    Map<String, Integer> statusMap = new HashMap<>();


    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";
    private static final String EXTRA_COURSE_NAME = "com.example.myapp3.course_name";
    private static final String EXTRA_COURSE_START = "com.example.myapp3.course_start";
    private static final String EXTRA_COURSE_END = "com.example.myapp3.course_end";
    private static final String EXTRA_COURSE_STATUS = "com.example.myapp3.course_status";
    private static final String EXTRA_COURSE_MENTOR_NAME = "com.example.myapp3.mentor_name";
    private static final String EXTRA_COURSE_MENTOR_PHONE = "com.example.myapp3.mentor_phone";
    private static final String EXTRA_COURSE_MENTOR_EMAIL = "com.example.myapp3.mentor_email";
    private static final String EXTRA_TERM_ID = "com.example.myapp3.term_id";
    private EditText courseMentorPhone;
    private EditText courseMentorEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_course);

        myDbConnection = new DbHelper(EditorCourseActivity.this);
        myDbConnection.getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // newCourse = new Course();

        courseTitleEditText = (EditText)findViewById(R.id.courseTitle);
        courseMentorEditText = (EditText)findViewById(R.id.courseMentor);
        courseMentorPhoneEditText = (EditText)findViewById(R.id.courseMentorPhone);
        courseMentorEmailEditText = (EditText)findViewById(R.id.courseMentorEmail);
        // courseStartEditText = (EditText)findViewById(R.id.courseStart);
        // courseEndEditText = (EditText)findViewById(R.id.courseEnd);
        courseStartTextView = (TextView) findViewById(R.id.courseStart);
        courseEndTextView = (TextView) findViewById(R.id.courseEnd);



        courseTitle = (EditText)findViewById(R.id.courseTitle);
        courseStart = (TextView) findViewById(R.id.courseStart);
       // courseStart = (EditText)findViewById(R.id.courseStart);
       // courseEnd = (EditText)findViewById(R.id.courseEnd);
        courseEnd = (TextView) findViewById(R.id.courseEnd);

        courseMentor = (EditText)findViewById(R.id.courseMentor);
        courseMentorPhone = (EditText)findViewById(R.id.courseMentorPhone);
        courseMentorEmail = (EditText)findViewById(R.id.courseMentorEmail);

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);
        courseTitleIntent = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        courseStartIntent = getIntent().getStringExtra(EXTRA_COURSE_START);
        courseEndIntent = getIntent().getStringExtra(EXTRA_COURSE_END);
        courseStatusIntent = getIntent().getStringExtra(EXTRA_COURSE_STATUS);

        courseMentorIntent = getIntent().getStringExtra(EXTRA_COURSE_MENTOR_NAME);
        courseMentorPhoneIntent = getIntent().getStringExtra(EXTRA_COURSE_MENTOR_PHONE);
        courseMentorEmailIntent = getIntent().getStringExtra(EXTRA_COURSE_MENTOR_EMAIL);
        termIdIntent = getIntent().getStringExtra(EXTRA_TERM_ID);

        courseTitleEditText.setText(courseTitleIntent);
        courseStartTextView.setText(courseStartIntent);
        // courseStartEditText.setText(courseStartIntent);
        courseEndTextView.setText(courseEndIntent);
        // courseEndEditText.setText(courseEndIntent);

        courseMentorEditText.setText(courseMentorIntent);
        courseMentorPhoneEditText.setText(courseMentorPhoneIntent);
        courseMentorEmailEditText.setText(courseMentorEmailIntent);


        statusSpinner = (Spinner)findViewById(R.id.courseStatus);


        String [] statusArray = {"Planned", "Dropped", "In Progress", "Completed"};


        ArrayAdapter<String> statusArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statusArray );


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

        statusMap.put("Planned", 0);
        statusMap.put("Dropped", 1);
        statusMap.put("In Progress", 2);
        statusMap.put("Completed", 3);

        if(statusMap.containsKey(courseStatusIntent)){
            statusSpinner.setSelection(statusMap.get(courseStatusIntent));

        }else{
            statusSpinner.setSelection(3);
        }

        Button startOfClass = (Button) findViewById(R.id.startClassDate);
        Button endOfClass = (Button) findViewById(R.id.endClassDate);

        startOfClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerNum = 1;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        endOfClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerNum = 0;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });


        Button startOfClassAlarm = (Button)findViewById(R.id.startAlarmBtn);
        Button endOfClassAlarm = (Button)findViewById(R.id.startAlarmBtn);

        startOfClassAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = courseStartTextView.getText().toString();
                Calendar calendar = Calendar.getInstance();

                int year = Integer.parseInt(startDate.substring(0,4));
                int month = Integer.parseInt(startDate.substring(5,7));
                int day = Integer.parseInt(startDate.substring(8));

                Toast.makeText(EditorCourseActivity.this, "An alert will go off at 12:01 am on Date: " + startDate, Toast.LENGTH_LONG).show();
                calendar.set(year, month - 1, day, 00,01,01);

                Intent intent = new Intent(EditorCourseActivity.this, AlarmReceiver.class);
                PendingIntent sender= PendingIntent.getBroadcast(EditorCourseActivity.this,0,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 1000, sender);
            }
        });

        endOfClassAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = courseStartTextView.getText().toString();
                Calendar calendar = Calendar.getInstance();

                int year = Integer.parseInt(startDate.substring(0,4));
                int month = Integer.parseInt(startDate.substring(5,7));
                int day = Integer.parseInt(startDate.substring(8));
                Toast.makeText(EditorCourseActivity.this, "An alert will go off at 12:01 am on Date: " + startDate, Toast.LENGTH_LONG).show();
                calendar.set(year, month - 1, day, 00,01,1);

                Intent intent = new Intent(EditorCourseActivity.this, AlarmReceiver.class);
                PendingIntent sender= PendingIntent.getBroadcast(EditorCourseActivity.this,0,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 1000, sender);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(calendar.getTime());

        // String dateString = DateFormat.getDateInstance().format(calendar.getTime());

        if(datePickerNum == 1){
            TextView startDate = (TextView) findViewById(R.id.courseStart);
            startDate.setText(dateString);
        }
        else{
            TextView endDate = (TextView) findViewById(R.id.courseEnd);
            endDate.setText(dateString);
        }
    }

    public void deleteCourse(View view){
        if(EditorTermActivity.deleteFromTerm){
            updateCourse(view);
            goBackToTermList();

        }else{
            int intCourseId = Integer.parseInt(courseIdIntent);

            myDbConnection.deleteRecord("DELETE FROM course WHERE courseID = " + intCourseId);

            goBackToMain();
        }
    }

    public void updateCourse(View view){

        String title = courseTitleEditText.getText().toString();
        String start = courseStartTextView.getText().toString();
        String end = courseEndTextView.getText().toString();

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

        String termId = "termId";

        myDbConnection.updateCourse(courseID, courseIdIntent, courseTitle, title, startDate, start, endDate, end, status, courseStatus, mentor, courseMentor, mentorPhone, courseMentorPhone, mentorEmail, courseMentorEmail, termId, courseTermId );

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

    public void openCourseAssessments(View view){
        Intent intent = new Intent(this, CourseAssessmentActivity.class);
        intent.putExtra(EXTRA_COURSE_ID, courseIdIntent);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();
    }
}


