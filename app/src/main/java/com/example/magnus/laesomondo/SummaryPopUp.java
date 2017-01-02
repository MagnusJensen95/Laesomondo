package com.example.magnus.laesomondo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.ViewStubCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by buller on 16/09/2016.
 */
public class SummaryPopUp extends Fragment {

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.summarypopup, container, false);

        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        tryAgain = (Button)v.findViewById(R.id.TryAgainButtonText);
        profile = (Button)v.findViewById(R.id.summaryPopUpTBD);
        scoreView = (TextView) v.findViewById(R.id.summaryPopUpScoreTBD);

        getActivity().getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.6));

        Bundle extras = getArguments();
        timeMillis = extras.getDouble("ReadingTime");
        wordsInText = extras.getInt("WordsIntText");
        textTitle = extras.getString("TextTitle");

        int timeMinutes = (int) Math.floor((timeMillis/1000)/60);
        int timeSeconds = (int) Math.floor(timeMillis/1000)%60;

        scoreView.setText(getString(R.string.summaryPopUpReadingTime, timeMinutes, timeSeconds));

        DBHandler database = new DBHandler(getActivity());

        database.addTestResult(textTitle, wordsInText, timeMillis);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getFragmentManager().beginTransaction().replace(R.id.container_main, new MainMenu());
    }



    public void onBackToMenu(View v){
        //TODO: Create variable for whether or not user is logged in or not.
        //TODO: If user is NOT logged in, remove Profile button, and take back
        //TODO: to REGULAR MAIN MENU (MainMenu.class).
        getFragmentManager().beginTransaction().replace(R.id.container_main, new MainMenu());
    }

    public void onProfile(View v){

        getFragmentManager().beginTransaction().replace(R.id.container_main, new UserProfile());
    }
}
