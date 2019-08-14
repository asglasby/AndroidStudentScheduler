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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapp3.Models.Assessment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditorAssessmentActivity extends OptionsMenuActivity implements DatePickerDialog.OnDateSetListener {
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
    private TextView goalDateTextView;
    private EditText courseIdEditText;
    private String assessmentId;
    private View view;
    Spinner assessmentSpinner;
    int assessmentTypePosition;
    Map<String, Integer> assessmentMap = new HashMap<>();

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDbConnection = new DbHelper(EditorAssessmentActivity.this);

        myDbConnection.getWritableDatabase();

        assessmentTitleEditText = (EditText)findViewById(R.id.assessment_title_txt);
       // assessmentTypeEditText = (EditText)findViewById(R.id.assessment_type_txt);
        assessmentDateEditText = (EditText)findViewById(R.id.assessment_date_txt);
        goalDateTextView = (TextView)findViewById(R.id.goalDate);
       // courseIdEditText = (EditText)findViewById(R.id.course_id_txt);


        assessmentTitle = (EditText)findViewById(R.id.assessment_title_txt);

        assessmentDate = (EditText)findViewById(R.id.assessment_date_txt);


        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        assessmentIdIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_ID);
        assessmentTitleIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_TITLE);
        assessmentTypeIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_TYPE);
        assessmentDateIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_DATE);
        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

        assessmentTitleEditText.setText(assessmentTitleIntent);

        assessmentDateEditText.setText(assessmentDateIntent);


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

        if(assessmentMap.containsKey(assessmentTypeIntent)){
            assessmentSpinner.setSelection(assessmentMap.get(assessmentTypeIntent));

        }else{
            assessmentSpinner.setSelection(0);
        }

        Button goalDatePicker = (Button) findViewById(R.id.goalDateBtn);

        goalDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        Button goalDateAlarm = (Button)findViewById(R.id.goalDateAlertBtn);

        goalDateAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String goalDate = goalDateTextView.getText().toString();
                Calendar calendar = Calendar.getInstance();

                int year = Integer.parseInt(goalDate.substring(0,4));
                int month = Integer.parseInt(goalDate.substring(5,7));
                int day = Integer.parseInt(goalDate.substring(8));
                Toast.makeText(EditorAssessmentActivity.this, "An alert will go off at 12:01 am on Date: " + goalDate, Toast.LENGTH_LONG).show();
                calendar.set(year, month - 1, day, 00,01,1);

                Intent intent = new Intent(EditorAssessmentActivity.this, AlarmReceiver.class);
                PendingIntent sender= PendingIntent.getBroadcast(EditorAssessmentActivity.this,0,intent,0);
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

        TextView goalDateTv = (TextView) findViewById(R.id.goalDate);
        goalDateTv.setText(dateString);

    }

    public void deleteAssessment(View View){
        int intAssessmentId = Integer.parseInt(assessmentIdIntent);
        myDbConnection.deleteRecord("DELETE FROM assessment WHERE assessmentID = " + intAssessmentId);
        goBackToMain();
    }

    public void updateAssessment(View view){

        String title = assessmentTitleEditText.getText().toString();

        String type;
        String date = assessmentDateEditText.getText().toString();

        String assessmentID = "assessmentID";
        String assessmentTitle = "assessmentTitle";
        String assessmentType = "assessmentType";
        String assessmentDate = "assessmentDate";
        String courseId = "courseId";

        Map<Integer, String> assessmentIntMap = new HashMap<>();
        assessmentIntMap.put(0, "Objective");
        assessmentIntMap.put(1, "Performance");

        type = assessmentIntMap.get(assessmentTypePosition);

        myDbConnection.updateAssessment(assessmentID, assessmentIdIntent, assessmentTitle, title, assessmentType, type, assessmentDate, date, courseId, courseIdIntent);


        goBackToMain();
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, CourseList.class);

        startActivity(intent);
    }

    public void openAssessmentNotes(View view){
        Intent intent = new Intent(this, AssessmentNoteListActivity.class);
        intent.putExtra(EXTRA_ASSESSMENT_ID, assessmentIdIntent);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();

    }
}
