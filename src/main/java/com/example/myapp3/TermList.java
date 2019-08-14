package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp3.Adapters.TermAdapter;
import com.example.myapp3.Models.Term;

import java.util.ArrayList;

public class TermList extends OptionsMenuActivity {

    private static final String EXTRA_ADD_UPDATE = "com.example.myapp3.add_update";
    private static final String EXTRA_TERM_ID = "com.example.myapp3.term_id";
    private static final String EXTRA_TERM_NAME = "com.example.myapp3.term_name";
    private static final String EXTRA_TERM_START = "com.example.myapp3.term_start";
    private static final String EXTRA_TERM_END = "com.example.myapp3.term_end";

    DbHelper myDbConnection;
    private static final String TAG = "TermList";
    public static ArrayList<Term> allTermsArrayList;
    ListView termListListView;
    TermAdapter termAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Sqliteopenhelper object
        myDbConnection = new DbHelper(TermList.this);

        // Create Database
        myDbConnection.getWritableDatabase();

        termListListView = (ListView)findViewById(R.id.TermList);
        loadTermData();

        // onclick listener to open term details.
        termListListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long l){

                String termName = allTermsArrayList.get(position).getTermTitle();
                String termStart = allTermsArrayList.get(position).getTermStart();
                String termEnd = allTermsArrayList.get(position).getTermEnd();
                int termIdfromArray = allTermsArrayList.get(position).getTermID();
                String termId = Integer.toString(termIdfromArray);

                Toast.makeText(TermList.this,"Term to String: "  + "termIdFromArray " + termIdfromArray +  allTermsArrayList.get(position).getTermTitle() + termStart+ termEnd+"  TermID:  " + allTermsArrayList.get(position).getTermID(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TermList.this, EditorTermActivity.class);

                intent.putExtra(EXTRA_ADD_UPDATE, "Add");
                intent.putExtra(EXTRA_TERM_ID, termId);
                intent.putExtra(EXTRA_TERM_NAME, termName);
                intent.putExtra(EXTRA_TERM_START, termStart);
                intent.putExtra(EXTRA_TERM_END, termEnd);

                startActivity(intent);
            }
        });
    }

    public void openEditorForNewTerm(View view) {
        Intent intent = new Intent(TermList.this, AddNewTermActivity.class);
        startActivity(intent);
    }

    public void loadTermData(){
        allTermsArrayList = myDbConnection.readRecords("SELECT * FROM term");

        termAdapter = new TermAdapter(TermList.this, allTermsArrayList);
        termListListView.setAdapter(termAdapter);
        termAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause(){
        super.onPause();

        myDbConnection.close();
        Toast.makeText(TermList.this, myDbConnection.getDatabaseName() + "Closed", Toast.LENGTH_SHORT).show();
    }
}
