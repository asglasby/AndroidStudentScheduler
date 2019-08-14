package com.example.myapp3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNewCourseActivity extends OptionsMenuActivity implements DatePickerDialog.OnDateSetListener {
    private EditText courseTitle;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText courseStatus;
    private EditText courseMentor;
    private EditText courseAssessment;
    private EditText courseMentorPhoneEditText;
    private EditText courseMentorEmailEditText;
    Spinner statusSpinner;
    Spinner assessmentSpinner;
    int courseStatusPosition;
    int assessmentTypePosition;
    int datePickerNum = 0;
    DbHelper myDbConnection;

    Map<String, Integer> statusMap = new HashMap<>();
    Map<String, Integer> assessmentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDbConnection = new DbHelper(AddNewCourseActivity.this);
        myDbConnection.getWritableDatabase();

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

    public void addCourseToDB(View view){
        EditText courseTitleEditText = (EditText)findViewById(R.id.courseTitle);
        TextView courseStartTextView = (TextView) findViewById(R.id.courseStart);
        TextView courseEndTextView = (TextView)findViewById(R.id.courseEnd);
        EditText courseMentorEditText = (EditText)findViewById(R.id.courseMentor);
        EditText courseMentorPhoneEditText = (EditText)findViewById(R.id.mentorPhone);
        EditText courseMentorEmailEditText = (EditText)findViewById(R.id.mentorEmail);

        String title = courseTitleEditText.getText().toString();
        String start = courseStartTextView.getText().toString();
        String end = courseEndTextView.getText().toString();

        String courseMentor = courseMentorEditText.getText().toString();
        String courseTermId = "";
        String courseStatus;

        String courseMentorPhone = courseMentorPhoneEditText.getText().toString();
        String courseMentorEmail = courseMentorEmailEditText.getText().toString();

        String courseTitle = "courseTitle";
        String startDate = "startDate";
        String endDate = "endDate";
        String status = "status";
        String mentor = "mentorName";
        String mentorPhone = "mentorPhone";
        String mentorEmail = "mentorEmail";
        String termId = "termId";

        Map<Integer, String> statusIntMap = new HashMap<>();
        statusIntMap.put(0, "Planned");
        statusIntMap.put(1, "Dropped");
        statusIntMap.put(2, "In Progress");
        statusIntMap.put(3, "Completed");
        courseStatus = statusIntMap.get(courseStatusPosition);

        myDbConnection.addCourse(courseTitle, title, startDate, start, endDate, end, status, courseStatus, mentor, courseMentor, mentorPhone, courseMentorPhone, mentorEmail, courseMentorEmail, termId, courseTermId );

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
