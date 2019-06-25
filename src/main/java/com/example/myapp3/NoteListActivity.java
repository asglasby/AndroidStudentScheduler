package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp3.Adapters.NoteAdapter;
import com.example.myapp3.Models.Note;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {
    private String courseIdIntent;
    ArrayList<Note> notesForThisCourseList = new ArrayList<Note>();

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_NOTE_ID = "com.example.myapp3.note_id";
    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    DbHelper myDbConnection;
    private static final String TAG = "NoteList";
    public static ArrayList<Note> allNotesArrayList;
    ListView noteListListView;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(NoteListActivity.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        courseIdIntent = getIntent().getStringExtra(EXTRA_COURSE_ID);

        noteListListView = (ListView)findViewById(R.id.NoteList);
        loadNoteData();

        // onclick listener to open course details.
        noteListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int noteIdFromArray = notesForThisCourseList.get(position).getNoteID();
                String noteText = notesForThisCourseList.get(position).getNoteText();
                int courseIdFromArray = notesForThisCourseList.get(position).getCourseID();

                String courseId = Integer.toString(courseIdFromArray);
                String noteId = Integer.toString(noteIdFromArray);

                Intent intent = new Intent(NoteListActivity.this, EditorNoteActivity.class);

                intent.putExtra(EXTRA_ADD_UPDATE, "Add");
                intent.putExtra(EXTRA_COURSE_ID, courseId);
                intent.putExtra(EXTRA_NOTE_TEXT, noteText);
                intent.putExtra(EXTRA_NOTE_ID, noteId);

                startActivity(intent);
            }
        });


    }

    public void openEditorForNewNote(View view){
        Intent intent = new Intent(this, AddNewNoteActivity.class);

        intent.putExtra(EXTRA_COURSE_ID, courseIdIntent);
        startActivity(intent);
    }

    public void loadNoteData(){
        allNotesArrayList = myDbConnection.readNoteRecords("SELECT * FROM note");

        int courseId = Integer.parseInt(courseIdIntent);

        for(Note note: allNotesArrayList){
            if(note.getCourseID() == courseId){
                notesForThisCourseList.add(note);
            }
        }

        // noteAdapter = new NoteAdapter(this, allNotesArrayList);
        noteAdapter = new NoteAdapter(this, notesForThisCourseList);
        noteListListView.setAdapter(noteAdapter);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();
        // Toast.makeText(CourseList.this, myDbConnection.getDatabaseName() + "Closed", Toast.LENGTH_SHORT).show();
    }

}
