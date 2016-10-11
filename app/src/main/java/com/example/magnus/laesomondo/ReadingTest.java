package com.example.magnus.laesomondo;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Timer;

public class ReadingTest extends AppCompatActivity {

    String toLoad;
    String textTitle;
    Bundle bundle;
    String typeReadingTest;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test);
        final double readingTestReadingTimeStart = System.currentTimeMillis();
        tv = (TextView) findViewById(R.id.readingTestReadingMaterial);

        bundle = getIntent().getExtras();
        typeReadingTest = bundle.getString("readingTestType");

        final Intent intent = new Intent(getBaseContext(), SummaryPopUp.class);

        int wordCount=0;
        String[] wordArray;
        switch (typeReadingTest) {
            case "readingTestOriginal":
                toLoad = bundle.getString("TextToLoad");
                //textTitle = bundle.getString("Titel");
                wordArray = toLoad.split("\\s+");
                wordCount = wordArray.length;
                //intent.putExtra("TextTitel", textTitle);
                intent.putExtra("WordsInText", wordCount);
                tv.setText(toLoad);
                break;
            case "readingTestNetTest":
                toLoad = bundle.getString("TextToLoad");
                textTitle = bundle.getString("Titel");
                wordArray = toLoad.split("\\s+");
                wordCount = wordArray.length;
                intent.putExtra("TextTitel", textTitle);
                intent.putExtra("WordsInText", wordCount);
                tv.setText(toLoad);
                break;
            default:
                break;
        }

        tv.setMovementMethod(new ScrollingMovementMethod());
        Button readingTestStopShowPopUp = (Button) findViewById(R.id.readingTestStopButton);
        readingTestStopShowPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double readingTestReadingTimeStop = System.currentTimeMillis()-readingTestReadingTimeStart;
                intent.putExtra("ReadingTime", readingTestReadingTimeStop);
                startActivity(intent);
            }
        });
    }
}