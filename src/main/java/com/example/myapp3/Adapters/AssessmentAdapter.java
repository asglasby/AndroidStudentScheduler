package com.example.myapp3.Adapters;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapp3.Models.Assessment;
import com.example.myapp3.R;
import java.util.ArrayList;

public class AssessmentAdapter extends BaseAdapter {

    Context context;
    ArrayList<Assessment> assessmentArrayList;
    public AssessmentAdapter(Context context, ArrayList<Assessment> assessmentArrayList){
        this.context = context;
        this.assessmentArrayList = assessmentArrayList;
    }

    @Override
    public int getCount() {
        return this.assessmentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return assessmentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.my_assessment_listview,null);

        TextView assessmentId = (TextView)convertView.findViewById(R.id.assessment_id_txt);
        TextView assessmentTitle = (TextView)convertView.findViewById(R.id.assessment_title_txt);
        TextView assessmentType = (TextView)convertView.findViewById(R.id.assessment_type_txt);
        TextView assessmentDate = (TextView)convertView.findViewById(R.id.assessment_date_txt);
        TextView courseId = (TextView)convertView.findViewById(R.id.course_id_txt);


        Assessment assessment = assessmentArrayList.get(position);

        assessmentId.setText(String.valueOf(assessment.getAssessmentID()));
        assessmentTitle.setText(assessment.getAssessmentTitle());
        assessmentType.setText(assessment.getAssessmentType());
        assessmentDate.setText(assessment.getAssessmentDate());
        courseId.setText(String.valueOf(assessment.getCourseID()));



        return convertView;
    }

}
