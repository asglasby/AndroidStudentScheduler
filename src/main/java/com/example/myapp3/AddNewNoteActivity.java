package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNoteActivity extends AppCompatActivity {
    private EditText noteText;
    private String courseIdIntent;

    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    DbHelper myDbConnection;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

    }

    public void addNoteToDB(View view){
        myDbConnection = new DbHelper(AddNewNoteActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();
        EditText noteTextEditText = (EditText)findViewById(R.id.noteText);

        String text = noteTextEditText.getText().toString();

        String noteText = "noteText";
        String courseID = "courseID";

        myDbConnection.addNote(noteText, text, courseID, courseIdIntent);

        myDbConnection.close();

        goBackToMain();
       // finish();

    }

    public void goBackToMain(){
        Intent intent = new Intent(this, CourseList.class);

        startActivity(intent);
    }
}
