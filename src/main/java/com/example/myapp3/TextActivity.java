package com.example.myapp3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TextActivity extends AppCompatActivity {
    private String noteTextIntent;
    private String noteText;
    EditText noteTextEditText;
    EditText phoneNumberEditText;
    private String mode;

    private static final String EXTRA_NOTE_TEXT = "com.example.myapp3.note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        noteTextEditText = (EditText)findViewById(R.id.note_txt);
        phoneNumberEditText = (EditText)findViewById(R.id.phoneTxt);


        noteTextIntent = getIntent().getStringExtra(EXTRA_NOTE_TEXT);

        noteTextEditText.setText(noteTextIntent);

    }

    public void sendText(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},0);
        }
        finish();
    }

    private void MyMessage() {
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String message = noteTextEditText.getText().toString().trim();

        if(!phoneNumberEditText.getText().toString().equals("") || !noteTextEditText.getText().toString().equals("")){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please Enter Number and Message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if(grantResults.length >=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }else{
                    Toast.makeText(this, "You don't have Required Permission", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
