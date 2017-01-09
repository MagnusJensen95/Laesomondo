package com.example.magnus.laesomondo.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.dataclasses.DBHandler;
import com.example.magnus.laesomondo.dataclasses.LixCalculator;
import com.example.magnus.laesomondo.dataclasses.StatisticsCalculator;

/**
 * Created by buller on 16/09/2016.
 */
public class SummaryPopUp extends DialogFragment {

    Button tryAgain;
    Button profile;
    TextView scoreView;
    DisplayMetrics dm;

    Double timeMillis;
    int wordsInText;
    String textTitle;
    String textText;

    //TODO: Implement userLoggedIn variable for determining if profile
    //TODO: button should be shown. See onDestroy for more.
    //This is due to us having more than one test. As such,
    //It cannot lead back to ReadingTest prereqs, since that
    //is specific to that one text, and this class isn't.


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.summarypopup, container, false);

       // dm = new DisplayMetrics();
        //getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        tryAgain = (Button)v.findViewById(R.id.TryAgainButtonText);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right )
                        .replace(R.id.container_main,
                                new MainMenu()).addToBackStack(null).commit();
                dismiss();
            }
        });
        profile = (Button)v.findViewById(R.id.summaryPopUpTBD);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right )
                        .replace(R.id.container_main,
                                new UserProfile()).addToBackStack(null).commit();
                dismiss();

            }
        });
        scoreView = (TextView) v.findViewById(R.id.summaryPopUpScoreTBD);

       // getActivity().getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.6));

        Bundle extras = getArguments();
        timeMillis = extras.getDouble("ReadingTime");
        wordsInText = extras.getInt("WordsInText");
        textTitle = extras.getString("TextTitle");
        textText = extras.getString("TextToLoad");

        int timeMinutes = (int) Math.floor((timeMillis/1000)/60);
        int timeSeconds = (int) Math.floor(timeMillis/1000)%60;

        scoreView.setText(getString(R.string.summaryPopUpReadingTime, timeMinutes, timeSeconds));

        DBHandler database = new DBHandler(getActivity());
        double[] d = StatisticsCalculator.calculateComparativeReadingTime(timeMillis/1000, wordsInText);

        for( int i = 0; i < 100; i++){
            if(true) {
                database.addTestResult(textTitle, wordsInText, timeMillis / 1000, LixCalculator.calcLix(textText),
                        d); //TODO: change the 2 methods to return actual values.
            }
        }

      //  getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
     //   getDialog().getWindow().setBackgroundDrawable(null);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getFragmentManager().beginTransaction().replace(R.id.container_main, new MainMenu());
    }






    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {

            dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);


            dialog.getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.8));
            dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }
}
