package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import com.example.myapp3.Models.Note;


public class EditorNoteActivity extends OptionsMenuActivity {
    private EditText noteText;
    private EditText courseId;
    private String mode;
    private Note newNote;
    private String noteIdIntent;
    private String courseIdIntent;
    private String noteTextIntent;
    private EditText noteTextEditText;
    private EditText courseIdEditText;

    DbHelper myDbConnection;

    SQLiteDatabase sqLiteDatabase;

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";
    private static final String EXTRA_NOTE_ID = "com.example.myapp3.note_id";
    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDbConnection = new DbHelper(EditorNoteActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

       // newNote = new Note();

        noteTextEditText = (EditText)findViewById(R.id.noteText);

        noteText = (EditText)findViewById(R.id.noteText);

        // mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        noteTextIntent = getIntent().getStringExtra(EXTRA_NOTE_TEXT);
        noteIdIntent = getIntent().getStringExtra(EXTRA_NOTE_ID);
        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

        noteTextEditText.setText(noteTextIntent);

    }

    public void updateNote(View view) {
        String note = noteTextEditText.getText().toString();

        String courseID = "courseID";
        String noteText = "noteText";
        String noteID = "noteID";

        myDbConnection.updateNote(noteID, noteIdIntent, noteText, note, courseID, courseIdIntent);

        goBackToMain();
        //finish();
    }

    public void deleteNote(View view) {
        int intNoteId = Integer.parseInt(noteIdIntent);
        myDbConnection.deleteRecord("DELETE FROM note WHERE noteID = " + intNoteId);

        goBackToMain();

    }

    public void textNote(View view) {
        Intent intent = new Intent(EditorNoteActivity.this, TextActivity.class);
        String note = noteTextEditText.getText().toString();
        intent.putExtra(EXTRA_NOTE_TEXT, note);
        startActivity(intent);
    }

    public void emailNote(View view) {
        Intent intent = new Intent(EditorNoteActivity.this, EmailActivity.class);
        String note = noteTextEditText.getText().toString();
        intent.putExtra(EXTRA_NOTE_TEXT, note);
        startActivity(intent);
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
