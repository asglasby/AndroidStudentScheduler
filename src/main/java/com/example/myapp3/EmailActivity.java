package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends OptionsMenuActivity {
    private String noteTextIntent;
    EditText noteTextEditText;
    EditText toEditText;
    EditText subjectEditText;

    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteTextEditText = (EditText)findViewById(R.id.note_message_txt);
        noteTextIntent = getIntent().getStringExtra(EXTRA_NOTE_TEXT);
        noteTextEditText.setText(noteTextIntent);

        toEditText = findViewById(R.id.to_txt);
        subjectEditText = findViewById(R.id.subject_txt);

        Button buttonSend = findViewById(R.id.button_send_email);
        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendMail();
            }
        });
    }

    private void sendMail(){
        String recipientList = toEditText.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = subjectEditText.getText().toString();
        String message = noteTextEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Please choose an Email Client"));

        finish();
    }
}
