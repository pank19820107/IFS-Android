package com.indofantasysports.indofantasysports;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Pankaj on 29/10/2017 AD.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private List<String> questions;
    private HashMap<String, List<String>> answers;
    private Context ctx;

    ExpandableListViewAdapter(Context ctx, List<String> questions, HashMap<String, List<String>> answers){

        this.ctx = ctx;
        this.questions = questions;
        this.answers = answers;

    }
    @Override
    public int getGroupCount() {
        return questions.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return answers.get(questions.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return questions.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return answers.get(questions.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String question_head = (String) this.getGroup(groupPosition);
        if(convertView == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.question_layout, null);

        }
        TextView textView = (TextView) convertView.findViewById(R.id.questionTxtV);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(35,35,35,35);
        textView.setTextSize(18);
        textView.setTextColor(Color.WHITE);
        textView.setAllCaps(true);
        textView.setText(question_head);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String answer_head = (String) this.getChild(groupPosition, childPosition);
        if(convertView == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.answer_layout, null);

        }
        TextView textView = (TextView) convertView.findViewById(R.id.answerTxtV);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(answer_head);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
