package com.example.myapp3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.myapp3.R;
import com.example.myapp3.Models.Term;
import java.util.ArrayList;

public class TermAdapter extends BaseAdapter {
    Context context;
    ArrayList<Term> termArrayList;
    public TermAdapter(Context context, ArrayList<Term> termArrayList){
        this.context = context;
        this.termArrayList = termArrayList;
    }

    @Override
    public int getCount() {
        return this.termArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return termArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.my_custom_listview,null);
        // TextView t1_id = (TextView)convertView.findViewById(R.id.id_txt);
        TextView t2_termTitle = (TextView)convertView.findViewById(R.id.title_txt);
        TextView t3_termStart = (TextView)convertView.findViewById(R.id.start_txt);
        TextView t4_termEnd = (TextView)convertView.findViewById(R.id.end_txt);

        Term term = termArrayList.get(position);

        // t1_id.setText(String.valueOf(term.getTermID()));
        t2_termTitle.setText(term.getTermTitle());
        t3_termStart.setText(term.getTermStart());
        t4_termEnd.setText(term.getTermEnd());

        return convertView;
    }
}
