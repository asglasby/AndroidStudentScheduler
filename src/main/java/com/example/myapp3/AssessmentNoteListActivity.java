package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapp3.Adapters.AssessmentNoteAdapter;
import com.example.myapp3.Adapters.NoteAdapter;
import com.example.myapp3.Models.AssessmentNote;
import com.example.myapp3.Models.Note;

import java.util.ArrayList;

public class AssessmentNoteListActivity extends OptionsMenuActivity {
    private String assessmentIdIntent;
    ArrayList<AssessmentNote> notesForThisAssessmentList = new ArrayList<AssessmentNote>();

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_NOTE_ID = "com.example.myapp3.note_id";
    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";
    private static final String EXTRA_ASSESSMENT_ID = "com.example.myapp3.assessment_id";

    DbHelper myDbConnection;
    private static final String TAG = "NoteList";
    public static ArrayList<AssessmentNote> allNotesArrayList;
    ListView noteListListView;
    AssessmentNoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_note_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(AssessmentNoteListActivity.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        assessmentIdIntent = getIntent().getStringExtra(EXTRA_ASSESSMENT_ID);

        noteListListView = (ListView)findViewById(R.id.NoteList);
        loadNoteData();

        // onclick listener to open course details.
        noteListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int noteIdFromArray = notesForThisAssessmentList.get(position).getNoteID();
                String noteText = notesForThisAssessmentList.get(position).getNoteText();
                int assessmentIdFromArray = notesForThisAssessmentList.get(position).getAssessmentID();

                String assessmentId = Integer.toString(assessmentIdFromArray);
                String noteId = Integer.toString(noteIdFromArray);

                Intent intent = new Intent(AssessmentNoteListActivity.this, EditorAssessmentNoteActivity.class);

                intent.putExtra(EXTRA_ADD_UPDATE, "Add");
                intent.putExtra(EXTRA_ASSESSMENT_ID, assessmentId);
                intent.putExtra(EXTRA_NOTE_TEXT, noteText);
                intent.putExtra(EXTRA_NOTE_ID, noteId);

                startActivity(intent);
            }
        });
    }

    public void openEditorForNewNote(View view){
        Intent intent = new Intent(this, AddNewAssessmentNoteActivity.class);

        intent.putExtra(EXTRA_ASSESSMENT_ID, assessmentIdIntent);
        startActivity(intent);
    }

    public void loadNoteData(){
        allNotesArrayList = myDbConnection.readAssessmentNoteRecords("SELECT * FROM assessmentnote");

        int assessmentId = Integer.parseInt(assessmentIdIntent);

        for(AssessmentNote note: allNotesArrayList){
            if(note.getAssessmentID() == assessmentId){
                notesForThisAssessmentList.add(note);
            }
        }

        // noteAdapter = new NoteAdapter(this, allNotesArrayList);
        noteAdapter = new AssessmentNoteAdapter(this, notesForThisAssessmentList);
        noteListListView.setAdapter(noteAdapter);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause(){
        super.onPause();
        myDbConnection.close();
    }
}
