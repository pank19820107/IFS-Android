package com.indofantasysports.indofantasysports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpDeskActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewContent);
        List<String> questions = new ArrayList<String>();
        List<String> q1 = new ArrayList<String>();
        List<String> q2 = new ArrayList<String>();
        List<String> q3 = new ArrayList<String>();
        List<String> q4 = new ArrayList<String>();
        HashMap<String, List<String>> answers = new HashMap<String, List<String>>();
        String question_items[] = getResources().getStringArray(R.array.question);
        String a1[] = getResources().getStringArray(R.array.answer_one);
        String a2[] = getResources().getStringArray(R.array.answer_two);
        String a3[] = getResources().getStringArray(R.array.answer_three);
        String a4[] = getResources().getStringArray(R.array.answer_four);
        for (String question_str : question_items){
            questions.add(question_str);
        }
        for (String ans_str : a1){
            q1.add(ans_str);
        }
        for (String ans_str : a2){
            q2.add(ans_str);
        }
        for (String ans_str : a3){
            q3.add(ans_str);
        }
        for (String ans_str : a4){
            q4.add(ans_str);
        }
        answers.put(questions.get(0), q1);
        answers.put(questions.get(1), q2);
        answers.put(questions.get(2), q3);
        answers.put(questions.get(3), q4);
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(this, questions, answers);
        expandableListView.setAdapter(expandableListViewAdapter);

    }
}
