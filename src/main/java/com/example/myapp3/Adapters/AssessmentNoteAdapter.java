package com.example.myapp3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp3.Models.AssessmentNote;
// import com.example.myapp3.Models.Note;
import com.example.myapp3.R;

import java.util.ArrayList;

public class AssessmentNoteAdapter extends BaseAdapter {
    Context context;
    ArrayList<AssessmentNote> noteArrayList;

    public AssessmentNoteAdapter(Context context, ArrayList<AssessmentNote> noteArrayList){
        this.context = context;
        this.noteArrayList = noteArrayList;
    }

    @Override
    public int getCount() {
        return this.noteArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.assessment_note_listview,null);

        TextView text = (TextView)convertView.findViewById(R.id.noteText);
       // TextView assessmentId = (TextView)convertView.findViewById(R.id.assessmentId);

        AssessmentNote note = noteArrayList.get(position);

        text.setText(note.getNoteText());
       // assessmentId.setText(String.valueOf(note.getAssessmentID()));

        return convertView;
    }
}
