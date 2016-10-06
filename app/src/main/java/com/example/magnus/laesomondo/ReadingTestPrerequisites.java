package com.example.magnus.laesomondo;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.ArrayList;

public class ReadingTestPrerequisites extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_prerequisites);



    }

    public void startReadingTest(View v) {
        //cm.start();

        Intent intent = new Intent(getBaseContext(), ReadingTest.class);
        intent.putExtra("timeStart", System.currentTimeMillis());
        startActivity(intent);
    }
    //implement functionality, as well as cm start button (stop button in reading test).
}

