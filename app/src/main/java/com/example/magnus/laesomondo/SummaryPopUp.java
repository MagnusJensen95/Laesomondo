package com.example.magnus.laesomondo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.ViewStubCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by buller on 16/09/2016.
 */
public class SummaryPopUp extends AppCompatActivity {

    Button tryAgain;
    Button profile;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summarypopup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        tryAgain = (Button)findViewById(R.id.TryAgainButtonText);
        profile = (Button)findViewById(R.id.summaryPopUpTBD);

        getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.6));

        TextView scoreView = (TextView) findViewById(R.id.summaryPopUpScoreTBD);
        scoreView.setTextColor(Color.WHITE);

        Bundle extras = getIntent().getExtras();
        Double timeMillis = extras.getDouble("readingTime");

        int timeMinutes = (int) Math.floor((timeMillis/1000)/60);
        int timeSeconds = (int) Math.floor(timeMillis/1000)%60;

        scoreView.setText(getString(R.string.summaryPopUpReadingTime, timeMinutes, timeSeconds));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent switchActivity = new Intent(this, MainMenu.class);

        startActivity(switchActivity);
    }

    public void onTryAgain(View v){

        Intent goTo = new Intent(this, ReadingTestPrerequisites.class);

        startActivity(goTo);
    }

    public void onProfile(View v){

        Intent goTo = new Intent(this, UserProfile.class);

        startActivity(goTo);
    }
}
