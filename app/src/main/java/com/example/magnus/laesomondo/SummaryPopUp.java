package com.example.magnus.laesomondo;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    TextView scoreView;
    DisplayMetrics dm;

    Double timeMillis;
    int wordsInText;
    String textTitle;

    //TODO: Implement userLoggedIn variable for determining if profile
    //TODO: button should be shown. See onDestroy for more.
    //This is due to us having more than one test. As such,
    //It cannot lead back to ReadingTest prereqs, since that
    //is specific to that one text, and this class isn't.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summarypopup);

        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        tryAgain = (Button)findViewById(R.id.TryAgainButtonText);
        profile = (Button)findViewById(R.id.summaryPopUpTBD);
        scoreView = (TextView) findViewById(R.id.summaryPopUpScoreTBD);

        getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.6));

        Bundle extras = getIntent().getExtras();
        timeMillis = extras.getDouble("ReadingTime");
        wordsInText = extras.getInt("WordsIntText");
        textTitle = extras.getString("TextTitle");

        int timeMinutes = (int) Math.floor((timeMillis/1000)/60);
        int timeSeconds = (int) Math.floor(timeMillis/1000)%60;

        scoreView.setText(getString(R.string.summaryPopUpReadingTime, timeMinutes, timeSeconds));

        DBHandler database = new DBHandler(this);

        database.addTestResult(textTitle, wordsInText, timeMillis);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent switchActivity = new Intent(this, MainMenu.class);
        startActivity(switchActivity);
    }

    public void onBackToMenu(View v){
        //TODO: Create variable for whether or not user is logged in or not.
        //TODO: If user is NOT logged in, remove Profile button, and take back
        //TODO: to REGULAR MAIN MENU (MainMenu.class).
        Intent goTo = new Intent(this, MainMenu.class);

        startActivity(goTo);
    }

    public void onProfile(View v){

        Intent goTo = new Intent(this, UserProfile.class);

        startActivity(goTo);
    }
}
