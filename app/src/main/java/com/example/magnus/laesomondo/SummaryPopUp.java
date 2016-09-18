package com.example.magnus.laesomondo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by buller on 16/09/2016.
 */
public class SummaryPopUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summarypopup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.6));

        TextView scoreView = (TextView) findViewById(R.id.summaryPopUpScoreTBD);
        scoreView.setTextColor(Color.WHITE);

        Bundle extras = getIntent().getExtras();
        Double timeMillis = extras.getDouble("readingTime");

        int timeMinutes = (int) Math.floor((timeMillis/1000)/60);
        int timeSeconds = (int) Math.floor(timeMillis/1000)%60;

        scoreView.setText(getString(R.string.summaryPopUpReadingTime, timeMinutes, timeSeconds));

    }
}
