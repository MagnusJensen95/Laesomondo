package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;

public class ReadingTest extends Fragment {

    String toLoad;
    String textTitle;

    Bundle bundle;
    String typeReadingTest;
    TextView tv;

    private SharedPreferences prefs;
    private int fontSize,fontColor,backgroundColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reading_test, container, false);

        final double readingTestReadingTimeStart = System.currentTimeMillis();
        tv = (TextView) v.findViewById(R.id.readingTestReadingMaterial);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int getFontSize = prefs.getInt("textSize",fontSize);
        int getFontColor = prefs.getInt("fontColor",fontColor);
        int getBackgroundColor = prefs.getInt("backgroundColor",backgroundColor);

       final AppCompatActivity actionBar =  (AppCompatActivity) getActivity();
        actionBar.getSupportActionBar().hide();
        bundle = getArguments();
        typeReadingTest = bundle.getString("readingTestType");

        final Bundle toSummary = new Bundle();


        int wordCount=0;
        String[] wordArray;

        if(getFontSize == 0){
            //skal ikke gøre noget
        }
        else tv.setTextSize(getFontSize);
        if(getFontColor == 0){
            //skal ikke gøre noget
        }
        else tv.setTextColor(getFontColor);
        if(getBackgroundColor == 0){
            //skal ikke gøre noget
        }
        else tv.setBackgroundColor(getBackgroundColor);
        switch (typeReadingTest) {
            case "readingTestOriginal":
                toLoad = bundle.getString("TextToLoad");
                //textTitle = bundle.getString("Titel");
                wordArray = toLoad.split("\\s+");
                wordCount = wordArray.length;
                toSummary.putString("TextTitle", "Lorem Ipsum");
                toSummary.putInt("WordsInText", wordCount);

                tv.setText(toLoad);
                break;
            case "readingTestNetTest":
                toLoad = bundle.getString("TextToLoad");
                textTitle = bundle.getString("Titel");
                wordArray = toLoad.split("\\s+");
                wordCount = wordArray.length;
                toSummary.putString("TextTitle", textTitle);
                toSummary.putInt("WordsInText", wordCount);
                if(getFontSize == 0){
                    //skal ikke gøre noget
                }
                else{tv.setTextSize(getFontSize);}
                tv.setText(toLoad);
                break;
            default:
                break;
        }

        tv.setMovementMethod(new ScrollingMovementMethod());
        Button readingTestStopShowPopUp = (Button) v.findViewById(R.id.readingTestStopButton);
        readingTestStopShowPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double readingTestReadingTimeStop = System.currentTimeMillis()-readingTestReadingTimeStart;
                toSummary.putDouble("ReadingTime", readingTestReadingTimeStop);
                toSummary.putString("TextToLoad", toLoad);
                SummaryPopUp summary = new SummaryPopUp();
                summary.setArguments(toSummary);
                summary.show(getFragmentManager(), "Jensen");
                actionBar.getSupportActionBar().show();


               //getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left, R.animator.exit_to_right, R.animator.enter_from_right ).add(R.id.container_main, summary).commit();
            }
        });
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    return true;
                }
                return false;
            }
        });
        return v;

    }


}