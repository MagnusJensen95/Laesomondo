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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test);
        final double readingTestReadingTimeStart = System.currentTimeMillis();

        //TODO: Somehow need to differentiate between different reading tests.
        //TODO: Use the intent extras "readingTestPrerequisites" and "readingTestWebView" for now.
        //TODO: Maybe switch case?

        Bundle textToRead = getIntent().getExtras();
        toLoad = textToRead.getString("TextToLoad");
        textTitle = textToRead.getString("Titel");

        TextView tv = (TextView) findViewById(R.id.readingTestReadingMaterial);

        String[] wordArray = toLoad.split("\\s+");

        final int wordCount = wordArray.length;


        tv.setText(toLoad);


        tv.setMovementMethod(new ScrollingMovementMethod());

        Button readingTestStopShowPopUp = (Button) findViewById(R.id.readingTestStopButton);
        readingTestStopShowPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double readingTestReadingTimeStop = System.currentTimeMillis()-readingTestReadingTimeStart;
                Intent intent = new Intent(getBaseContext(), SummaryPopUp.class);
                intent.putExtra("ReadingTime", readingTestReadingTimeStop);
                intent.putExtra("WordsInText", wordCount);
                intent.putExtra("TextTitel", textTitle);

                startActivity(intent);
            }
        });
    }
}