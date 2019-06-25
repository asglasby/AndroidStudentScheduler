package com.example.myapp3.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp3.R;

public class StatusSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;

    public StatusSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return myStatusSpinnerView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return myStatusSpinnerView(position, convertView, parent);
    }

    private View myStatusSpinnerView(int position, @Nullable View courseStatusView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.course_status_spinner, parent, false);

        TextView textView = (TextView) customView.findViewById(R.id.courseStatusTxt);

        // textView.setText(courseNames.get(position).getCourseName());
        //textView.setText(@course_status);

        return customView;

    }
}
