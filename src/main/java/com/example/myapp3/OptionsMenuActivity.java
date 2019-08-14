package com.example.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OptionsMenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.homeItem:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.termItem:
                intent = new Intent(this, TermList.class);
                startActivity(intent);
                return true;

            case R.id.courseItem:
                intent = new Intent(this, CourseList.class);
                startActivity(intent);
                return true;

            case R.id.assessmentItem:
                intent = new Intent(this, AssessmentList.class);
                startActivity(intent);
                return true;

            case R.id.subTermItem:
                intent = new Intent(this, TermDetail.class);
                startActivity(intent);
                return true;

            case R.id.subCourseItem:
                intent = new Intent(this, CourseDetail.class);
                startActivity(intent);
                return true;

            case R.id.subAssessmentItem:
                intent = new Intent(this, AssessmentDetail.class);
                startActivity(intent);
                return true;

            case R.id.progressItem:
                intent = new Intent(this, ProgressActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
