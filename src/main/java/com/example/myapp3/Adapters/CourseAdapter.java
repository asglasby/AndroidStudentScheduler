package com.example.myapp3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp3.Models.Course;
import com.example.myapp3.R;


import java.util.ArrayList;

public class CourseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Course> courseArrayList;
    public CourseAdapter(Context context, ArrayList<Course> courseArrayList){
        this.context = context;
        this.courseArrayList = courseArrayList;
    }

    @Override
    public int getCount() {
        return this.courseArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        convertView = inflater.inflate(R.layout.my_course_listview,null);

        TextView c2_courseTitle = (TextView)convertView.findViewById(R.id.name_txt);
        TextView c3_startData = (TextView)convertView.findViewById(R.id.start_txt);
        TextView c4_endDate = (TextView)convertView.findViewById(R.id.end_txt);
        //TextView c5_status = (TextView)convertView.findViewById(R.id.status_txt);
       // TextView c6_mentor = (TextView)convertView.findViewById(R.id.mentor_txt);
        TextView c7_assessment = (TextView)convertView.findViewById(R.id.assessment_txt);

        Course course = courseArrayList.get(position);

        c2_courseTitle.setText(course.getCourseName());
        c3_startData.setText(course.getCourseStart());
        c4_endDate.setText(course.getCourseEnd());
       // c5_status.setText(course.getStatus());
       // c6_mentor.setText(String.valueOf(course.getMentorID()));
        c7_assessment.setText(course.getAssessmentType());


        return convertView;
    }
}
