package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp3.Adapters.CourseAdapter;
import com.example.myapp3.Adapters.CourseSpinnerAdapter;
import com.example.myapp3.Models.Course;
import com.example.myapp3.Models.Term;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static java.sql.Types.NULL;

public class EditorTermActivity extends OptionsMenuActivity {

    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    private TextView textView;
    private String mode;
    private Term newTerm;
    private String termNameIntent;
    private String termStartIntent;
    private String termEndIntent;
    private Term oldTerm;
    private String oldTermName;
    private String oldTermStart;
    private String oldTermEnd;
    private EditText termNameEditText;
    private EditText termIdEditText;
    private EditText termStartEditText;
    private EditText termEndEditText;
    private String termId;
    DbHelper myDbConnection;
    public SQLiteDatabase sqLiteDatabase;
    CourseAdapter courseAdapter;
    Spinner simpleSpinner;
    List<Course> simpleList;
    int coursePosition;
    public static int termCoursePosition;
    ListView courseListListView;
    List<Course> availableCourseList = new ArrayList<>();
    public static ArrayList<Course> termCourseList = new ArrayList<>();
    public static ArrayList<Course> allCoursesArrayList = new ArrayList<>();
    public static boolean deleteFromTerm;


    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_TERM_ID = "com.example.myapp3.term_id";
    private static final String EXTRA_TERM_NAME = "com.example.myapp3.term_name";
    private static final String EXTRA_TERM_START = "com.example.myapp3.term_start";
    private static final String EXTRA_TERM_END = "com.example.myapp3.term_end";

    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";
    private static final String EXTRA_COURSE_NAME = "com.example.myapp3.course_name";
    private static final String EXTRA_COURSE_START = "com.example.myapp3.course_start";
    private static final String EXTRA_COURSE_END = "com.example.myapp3.course_end";
    private static final String EXTRA_COURSE_STATUS = "com.example.myapp3.course_status";
    private static final String EXTRA_COURSE_MENTOR_ID = "com.example.myapp3.mentor_id";
    private static final String EXTRA_COURSE_ASSESSMENT_TYPE = "com.example.myapp3.course_assessment_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_term);

        myDbConnection = new DbHelper(EditorTermActivity.this);
        myDbConnection.getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        courseListListView = (ListView)findViewById(R.id.CourseList);

        termNameEditText = (EditText)findViewById(R.id.termName);
        termStartEditText = (EditText)findViewById(R.id.termStart);
        termEndEditText = (EditText)findViewById(R.id.termEnd);

        termName = (EditText)findViewById(R.id.termName);
        termStart = (EditText)findViewById(R.id.termStart);
        termEnd = (EditText)findViewById(R.id.termEnd);

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        termNameIntent = getIntent().getStringExtra(EXTRA_TERM_NAME);
        termStartIntent = getIntent().getStringExtra(EXTRA_TERM_START);
        termEndIntent = getIntent().getStringExtra(EXTRA_TERM_END);
        termId = getIntent().getStringExtra(EXTRA_TERM_ID);  // Remember to convert the id back to an integer before performing any updates.

        termNameEditText.setText(termNameIntent);
        termStartEditText.setText(termStartIntent);
        termEndEditText.setText(termEndIntent);

        simpleSpinner = (Spinner)findViewById(R.id.courseSpinner);

        simpleList = myDbConnection.readCourseRecords("SELECT * FROM course");

        for(Course c: simpleList){
            if(c.getTermID() == NULL){
                availableCourseList.add(c);
            }
        }

        CourseSpinnerAdapter simpleAdapter = new CourseSpinnerAdapter(EditorTermActivity.this, R.layout.course_spinner_layout, availableCourseList);

        simpleSpinner.setAdapter(simpleAdapter);

        loadCoursesToTerm();

        simpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coursePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void deleteTerm(View view){
        if(termCourseList.isEmpty()){

            int intTermId = Integer.parseInt(termId);

            myDbConnection.deleteRecord("DELETE FROM term WHERE termID = " + intTermId);

            goBackToMain();
        }else{
            AppAlertDialog.alertType = 1;
            AppAlertDialog deleteAlert = new AppAlertDialog();
            deleteAlert.show(getSupportFragmentManager(), "AppAlertDialog");
        }
    }

    public void updateTerm(View view){

        String title = termNameEditText.getText().toString();
        String start = termStartEditText.getText().toString();
        String end = termEndEditText.getText().toString();

        String termID = "termID";
        String termTitle = "termTitle";
        String termStart = "termStart";
        String termEnd = "termEnd";

        myDbConnection.updateTerm(termID, termId, termTitle, title, termStart, start, termEnd, end);

        goBackToMain();
    }

    public void goBackToMain(){
        Intent intent = new Intent(EditorTermActivity.this, TermList.class);
        startActivity(intent);
    }

    public void goBackToEditorTermActivity(){
        Intent intent = new Intent(this, EditorTermActivity.class);
        startActivity(intent);
    }

    public void loadCourseSpinnerData(){
        ArrayList<String> listCourse = myDbConnection.getAllCourses();
        Spinner sp = (Spinner)findViewById(R.id.courseSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditorTermActivity.this, R.layout.course_spinner_layout, R.id.courseNameTxt, listCourse);
        sp.setAdapter(adapter);
    }


   public void addCourseToTerm(View view){

       int intTermId = Integer.parseInt(termId);
       int intCourseId = availableCourseList.get(coursePosition).getCourseID();

       myDbConnection.insertRecord("UPDATE course SET termID = " + intTermId + " WHERE courseID = " + intCourseId);

       availableCourseList.remove(coursePosition);
       loadCoursesToTerm();
   }

   public void loadCoursesToTerm(){
       termCourseList.clear();

       allCoursesArrayList = myDbConnection.readCourseRecords("SELECT * FROM course");

       for(Course c: allCoursesArrayList){
           if(c.getTermID() == Integer.parseInt(termId)){
               termCourseList.add(c);
           }
       }

       courseAdapter = new CourseAdapter(this, termCourseList);
       courseListListView.setAdapter(courseAdapter);
       courseAdapter.notifyDataSetChanged();

       courseListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               deleteFromTerm = true;
               termCoursePosition = position;
               String courseName = termCourseList.get(position).getCourseName();
               String courseStart = termCourseList.get(position).getCourseStart();
               String courseEnd = termCourseList.get(position).getCourseEnd();
               String status = termCourseList.get(position).getStatus();

               int termIdFromArray = termCourseList.get(position).getTermID();
               int courseIdFromArray = termCourseList.get(position).getCourseID();
               String courseId = Integer.toString(courseIdFromArray);

               String termId = Integer.toString(termIdFromArray);

               Intent intent = new Intent(EditorTermActivity.this, EditorCourseActivity.class);

               intent.putExtra(EXTRA_ADD_UPDATE, "Add");
               intent.putExtra(EXTRA_COURSE_ID, courseId);
               intent.putExtra(EXTRA_COURSE_NAME, courseName);
               intent.putExtra(EXTRA_COURSE_START, courseStart);
               intent.putExtra(EXTRA_COURSE_END, courseEnd);
               intent.putExtra(EXTRA_COURSE_STATUS, status);

               intent.putExtra(EXTRA_TERM_ID, termId);

               startActivity(intent);
           }
       });
   }

   public void deleteCourseFromTerm(){
        termCourseList.remove(termCoursePosition);
   }

    @Override
    protected void onPause(){
        super.onPause();
        myDbConnection.close();
    }
}
