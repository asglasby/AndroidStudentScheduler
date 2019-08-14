package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNoteActivity extends OptionsMenuActivity {
    private EditText noteText;
    private String courseIdIntent;

    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    DbHelper myDbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDbConnection = new DbHelper(AddNewNoteActivity.this);
        myDbConnection.getWritableDatabase();

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);
    }

    public void addNoteToDB(View view){

        EditText noteTextEditText = (EditText)findViewById(R.id.noteText);

        String text = noteTextEditText.getText().toString();

        String noteText = "noteText";
        String courseID = "courseID";

        myDbConnection.addNote(noteText, text, courseID, courseIdIntent);

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
