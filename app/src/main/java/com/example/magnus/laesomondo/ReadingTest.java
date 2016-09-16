package com.example.magnus.laesomondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReadingTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test);

        TextView tv = (TextView)findViewById(R.id.readingTestReadingMaterial);

        tv.setMovementMethod(new ScrollingMovementMethod());


    }
}
