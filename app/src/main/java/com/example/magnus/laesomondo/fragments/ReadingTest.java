package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.activities.DrawerActivity;
import com.example.magnus.laesomondo.fragments.SummaryPopUp;

import java.lang.reflect.Array;

public class ReadingTest extends Fragment {

    String toLoad;
    String textTitle;

    Bundle bundle;
    String typeReadingTest;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_reading_test, container, false);

        final double readingTestReadingTimeStart = System.currentTimeMillis();
        tv = (TextView) v.findViewById(R.id.readingTestReadingMaterial);

        bundle = getArguments();
        typeReadingTest = bundle.getString("readingTestType");

        final Bundle toSummary = new Bundle();

        int wordCount=0;
        String[] wordArray;
        switch (typeReadingTest) {
            case "readingTestOriginal":
                toLoad = bundle.getString("TextToLoad");
                //textTitle = bundle.getString("Titel");
                wordArray = toLoad.split("\\s+");
                wordCount = wordArray.length;
                toSummary.putString("TextTitle", textTitle);
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
               //getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left, R.animator.exit_to_right, R.animator.enter_from_right ).add(R.id.container_main, summary).commit();
            }
        });

        return v;
    }


}