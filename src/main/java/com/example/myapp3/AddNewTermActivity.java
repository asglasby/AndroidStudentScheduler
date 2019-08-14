package com.example.myapp3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewTermActivity extends OptionsMenuActivity {

    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    DbHelper myDbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_term);
        myDbConnection = new DbHelper(AddNewTermActivity.this);
        myDbConnection.getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addTermToDB(View view){

        EditText termNameEditText = (EditText)findViewById(R.id.termName);
        EditText termStartEditText = (EditText)findViewById(R.id.termStart);
        EditText termEndEditText = (EditText)findViewById(R.id.termEnd);

        String title = termNameEditText.getText().toString();
        String start = termStartEditText.getText().toString();
        String end = termEndEditText.getText().toString();

        String termTitle = "termTitle";
        String termStart = "termStart";
        String termEnd = "termEnd";
        myDbConnection.addRecord(termTitle, title, termStart, start, termEnd, end);

        goBackToMain();
    }

    public void goBackToMain(){
        Intent intent = new Intent(AddNewTermActivity.this, TermList.class);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        myDbConnection.close();
    }
}
