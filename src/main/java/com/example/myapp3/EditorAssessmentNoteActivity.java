package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.myapp3.Models.AssessmentNote;

public class EditorAssessmentNoteActivity extends OptionsMenuActivity {
    private String noteIdIntent;
    private String assessmentIdIntent;
    private String noteTextIntent;
    private EditText noteTextEditText;
    private EditText noteText;
    private EditText courseId;
    private String mode;
    private AssessmentNote newNote;

    DbHelper myDbConnection;

    SQLiteDatabase sqLiteDatabase;

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_ASSESSMENT_ID = "com.example.myapp3.assessment_id";
    private static final String EXTRA_NOTE_ID = "com.example.myapp3.note_id";
    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_assessment_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newNote = new AssessmentNote();

        noteTextEditText = (EditText)findViewById(R.id.noteText);

        noteText = (EditText)findViewById(R.id.noteText);

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        noteTextIntent = getIntent().getStringExtra(EXTRA_NOTE_TEXT);
        noteIdIntent = getIntent().getStringExtra(EXTRA_NOTE_ID);
        assessmentIdIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_ID);

        noteTextEditText.setText(noteTextIntent);
    }

    public void updateNote(View view) {
        myDbConnection = new DbHelper(EditorAssessmentNoteActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        String note = noteTextEditText.getText().toString();

        String assessmentID = "assessmentID";
        String noteText = "noteText";
        String noteID = "noteID";

        myDbConnection.updateNote(noteID, noteIdIntent, noteText, note, assessmentID, assessmentIdIntent);

        myDbConnection.close();

        finish();
    }

    public void deleteNote(View view) {
        myDbConnection = new DbHelper(EditorAssessmentNoteActivity.this);
        sqLiteDatabase = myDbConnection.getWritableDatabase();

        int intNoteId = Integer.parseInt(noteIdIntent);

        myDbConnection.deleteRecord("DELETE FROM assessmentnote WHERE noteID = " + intNoteId);

        myDbConnection.close();

        finish();
    }

    public void textNote(View view) {
        Intent intent = new Intent(EditorAssessmentNoteActivity.this, TextActivity.class);
        String note = noteTextEditText.getText().toString();
        intent.putExtra(EXTRA_NOTE_TEXT, note);
        startActivity(intent);
    }

    public void emailNote(View view) {
        Intent intent = new Intent(EditorAssessmentNoteActivity.this, EmailActivity.class);
        String note = noteTextEditText.getText().toString();
        intent.putExtra(EXTRA_NOTE_TEXT, note);
        startActivity(intent);
    }
}
