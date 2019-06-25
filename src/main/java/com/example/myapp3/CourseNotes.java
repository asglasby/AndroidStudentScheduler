package com.example.myapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CourseNotes extends AppCompatActivity {
    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_NOTE_ID = "com.example.myapp3.note_id";
    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";
    private static final String EXTRA_COURSE_ID = "com.example.myapp3.course_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_notes);
    }
}
