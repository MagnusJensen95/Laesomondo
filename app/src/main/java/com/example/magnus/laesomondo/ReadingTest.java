package com.example.magnus.laesomondo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test);

        final double readingTestReadingTimeStart = System.currentTimeMillis();

        Bundle textToRead = getIntent().getExtras();


        TextView tv = (TextView) findViewById(R.id.readingTestReadingMaterial);

       tv.setText(textToRead.getString("TextToLoad"));

        tv.setMovementMethod(new ScrollingMovementMethod());

        Button readingTestStopShowPopUp = (Button) findViewById(R.id.readingTestStopButton);
        readingTestStopShowPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double readingTestReadingTimeStop = System.currentTimeMillis()-readingTestReadingTimeStart;
                Intent intent = new Intent(getBaseContext(), SummaryPopUp.class);
                intent.putExtra("readingTime", readingTestReadingTimeStop);
                startActivity(intent);
            }
        });
    }
}