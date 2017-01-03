package com.example.magnus.laesomondo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.fragments.SummaryPopUp;

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
                intent.putExtra("TextTitle", textTitle);
                intent.putExtra("WordsInText", wordCount);
                tv.setText(toLoad);
                break;
            case "readingTestNetTest":
                toLoad = bundle.getString("TextToLoad");
                textTitle = bundle.getString("Titel");
                wordArray = toLoad.split("\\s+");
                wordCount = wordArray.length;
                intent.putExtra("TextTitle", textTitle);
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