package com.example.myapp3.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapp3.Models.Course;
import com.example.myapp3.R;

import java.util.List;

public class CourseSpinnerAdapter extends ArrayAdapter<Course> {
    private Context context;
    private List<Course> courseNames;

    public CourseSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, List<Course> courseNames){
        super(context, resource, courseNames);
        this.context = context;
        this.courseNames = courseNames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return myCourseSpinnerView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return myCourseSpinnerView(position, convertView, parent);
    }

    private View myCourseSpinnerView(int position, @Nullable View courseNameView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.course_spinner_layout, parent, false);

        TextView textView = (TextView) customView.findViewById(R.id.courseNameTxt);

        textView.setText(courseNames.get(position).getCourseName());

        return customView;

    }


}
