package com.example.timgor.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View[] qs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qs = new View[]{
                findViewById(R.id.q1),
                findViewById(R.id.q2),
                findViewById(R.id.q3)

        };


        ((LinearLayout)qs[2]).addView(createCheckBoxWithText("Tamamo no mae"));
        ((LinearLayout)qs[2]).addView(createCheckBoxWithText("Sakura Haruno"));
        ((LinearLayout)qs[2]).addView(createCheckBoxWithText("Rei Ayanami"));
        ((LinearLayout)qs[2]).addView(createCheckBoxWithText("Momiji Inubashiri"));

    }

    protected CheckBox createCheckBoxWithText(String s){
        CheckBox box = new CheckBox(this);
        box.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        box.setText(s);
        return box;
    }

    protected void onClick(View v){
        switch (v.getId()){
            case R.id.submitButton:
                int score = countScore();
                ((TextView)findViewById(R.id.scoreDisplay)).setText(String.format("You got %o points!",score));

        }
    }

    private int countScore() {
        int score = 0;
        if(((RadioGroup)qs[0]).getCheckedRadioButtonId() == R.id.q1_vignette){
            score += 1;
        }
        if(((EditText)qs[1]).getText().toString().equals("bruh what")){
            score += 1;
        }
        if(     !((CheckBox)((LinearLayout)qs[2]).getChildAt(0)).isChecked() &&
                ((CheckBox)((LinearLayout)qs[2]).getChildAt(1)).isChecked() &&
                ((CheckBox)((LinearLayout)qs[2]).getChildAt(2)).isChecked() &&
                !((CheckBox)((LinearLayout)qs[2]).getChildAt(3)).isChecked()){
            score += 1;

        }
        return score;
    }

    public static class Utility {
        public static void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }
}
