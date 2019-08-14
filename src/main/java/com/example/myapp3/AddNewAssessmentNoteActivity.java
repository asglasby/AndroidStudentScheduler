package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewAssessmentNoteActivity extends OptionsMenuActivity {
    private EditText noteText;
    private String assessmentIdIntent;

    private static final String EXTRA_ASSESSMENT_ID = "com.example.myapp3.assessment_id";

    DbHelper myDbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_assessment_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assessmentIdIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_ID);

        myDbConnection = new DbHelper(AddNewAssessmentNoteActivity.this);
        myDbConnection.getWritableDatabase();
    }

    public void addNoteToDB(View view){
        EditText noteTextEditText = (EditText)findViewById(R.id.noteText);

        String text = noteTextEditText.getText().toString();

        String noteText = "noteText";
        String assessmentID = "assessmentID";

        myDbConnection.addAssessmentNote(noteText, text, assessmentID, assessmentIdIntent);

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
