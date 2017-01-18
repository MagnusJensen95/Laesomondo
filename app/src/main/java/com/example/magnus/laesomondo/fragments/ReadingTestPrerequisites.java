package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.dataclasses.TextHandler;
import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Tobias on 06-01-2017.
 */

public class ReadingTestPrerequisites extends Fragment implements CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener {

    private CircleLayout circleLayout;
    private TextView selectedTextView;

    private SeekBar seekBarTime, seekBarDifficulty;
    private TextView seekBarTimeValue, seekBarDifficultyValue, readingTimeTxt, readingDifficultyTxt;
    private Button startTestButton;
    private View fgView;
    private int diff = 200;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fgView = inflater.inflate(R.layout.fragment_reading_test_prerequisites, container, false);

        //  Initialisere variablerne

        startTestButton = (Button) fgView.findViewById(R.id.btnStart);
        startTestButton.setVisibility(GONE);


        seekBarTime = (SeekBar) fgView.findViewById(R.id.seekBar1);
        seekBarTime.setVisibility(GONE);

        seekBarTimeValue = (TextView) fgView.findViewById(R.id.textView1);

        seekBarDifficulty = (SeekBar) fgView.findViewById(R.id.seekBar2);
        seekBarDifficulty.setVisibility(GONE);

        seekBarDifficultyValue = (TextView) fgView.findViewById(R.id.textView2);

        readingTimeTxt = (TextView) fgView.findViewById(R.id.TidLaese);

        readingDifficultyTxt = (TextView) fgView.findViewById(R.id.TextSvaerhedsgrad);

        // Sætter en changeListiner på, som gør at når seekbar får et input, skal den angivende tekst opdateres

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                if (progresValue == 0){
                    seekBar.setProgress(1);
                }
                seekBarTimeValue.setText(String.valueOf(seekBar.getProgress()) + " Minutter");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

// Sætter en changeListiner på, som gør at når seekbar får et input, skal den angivende tekst opdateres
        seekBarDifficulty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                // TODO Auto-generated method stub
                switch (progresValue) {
                    case 0:
                        seekBarDifficultyValue.setText("Let øvet");
                        diff = 150;
                        break;
                    case 1:
                        seekBarDifficultyValue.setText("Øvet");
                        diff = 200;
                        break;
                    case 2:
                        seekBarDifficultyValue.setText("Meget øvet");
                        diff = 250;
                        break;
                    case 3:
                        seekBarDifficultyValue.setText("Ekspert");
                        diff = 300;
                        break;
                    default:
                        break;
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // Sætter listeners til vores circle menu
        circleLayout = (CircleLayout) fgView.findViewById(R.id.circle_layout);
        circleLayout.setOnItemClickListener(this);
        circleLayout.setOnRotationFinishedListener(this);


        selectedTextView = (TextView) fgView.findViewById(R.id.selected_textView);

        // Finder navnet på den valgte menu ikon, og sætter teksten til dette navn

        String name = null;
        final View view = circleLayout.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }

        // sætter clickListener på kanppen der hedder læs
        selectedTextView.setText(name);
        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = seekBarTime.getProgress();
                int difficulty = seekBarDifficulty.getProgress();
                String theme = ((CircleImageView) circleLayout.getSelectedItem()).getName();
                if (theme.equals("Tilfældig")) {

                    int randomTitle = (int)(Math.random() * 5);
                            switch (randomTitle){

                                case 0:
                                    theme = "Historie";
                                    break;
                                case 1:
                                    theme = "Krimi";
                                    break;
                                case 2:
                                    theme = "Mad";
                                    break;
                                case 3:
                                    theme = "Natur";
                                    break;
                                case 4:
                                    theme = "Sport";
                                    break;
                                default:

                            }

                }

                // når der bliver klikket på læs, har vi en textHandler, som går ned i vores "lagersystem" og finder en text
                // som passer til hvad bruger har angivet med hensyn på længde og sværhedsgrad.

                TextHandler handler = new TextHandler();

                String parseString = handler.retrieveText(getActivity(), time, diff, theme.toLowerCase() + ".txt");

                Bundle b = new Bundle();
                ReadingTest frag = new ReadingTest();
                // Den sender disse værdier med over til ReadingTest, da de skal anvedes til brugerens resultat
                b.putString("readingTestType", "readingTestOriginal");
                b.putString("TextToLoad", parseString);
                b.putString("TextTitle", "Default Titel");
                frag.setArguments(b);

                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                        R.animator.exit_to_right, R.animator.enter_from_right).replace(R.id.container_main,
                        frag).addToBackStack(null).commit();


            }
        });
        return fgView;
    }


    // denne onItemClick er til vores circle menu, hvad der skal ske når man klikker på ikonet man har valgt
    @Override
    public void onItemClick(View view) {

        switch (view.getId()) {
            case R.id.historie:
                // først kaldes metoden initialzeOnclik, som sætter de forskellige elementer synlige og starter deres animation.
                // hvis der er nogle tekster der skal sættes, bliver dette også gjort.
                initializeOnClick(view);

                // denne if og for løkke, sletter alle de andre elementer som ikke er valgt, så der kun er det valgte ikon tilabge
                if (circleLayout.getChildCount() > 1) {

                    for (int i = 0; i < 5; i++) {
                        circleLayout.removeViewAt(circleLayout.getChildCount() - 1);
                    }
                }

                //Efter alle de andre ikoner er slettet, skal ikonet flyttes til center af skærmen, dette gøres ved hjælp af metoden moveViewToScreenCenter
                ImageView img_his = (ImageView) fgView.findViewById(R.id.historie);
                moveViewToScreenCenter(img_his);

                break;

            case R.id.krimi:
                // først kaldes metoden initialzeOnclik, som sætter de forskellige elementer synlige og starter deres animation.
                // hvis der er nogle tekster der skal sættes, bliver dette også gjort.
                ImageView img_krimi = (ImageView) fgView.findViewById(R.id.krimi);
                initializeOnClick(view);
                // da vi anvender et circle bibliotek, er der ikke nogle smart måde at få fat på det valgte ikon´s id
                // derfor bliver vi nød til at "hardcode" hvilke ikoner der skal slettes
                circleLayout.removeViewAt(5);
                circleLayout.removeViewAt(4);
                circleLayout.removeViewAt(3);
                circleLayout.removeViewAt(2);
                circleLayout.removeViewAt(0);
                //Efter alle de andre ikoner er slettet, skal ikonet flyttes til center af skærmen, dette gøres ved hjælp af metoden moveViewToScreenCenter
                moveViewToScreenCenter(img_krimi);

                break;
            case R.id.mad:
                // først kaldes metoden initialzeOnclik, som sætter de forskellige elementer synlige og starter deres animation.
                // hvis der er nogle tekster der skal sættes, bliver dette også gjort.
                ImageView img_mad = (ImageView) fgView.findViewById(R.id.mad);
                initializeOnClick(view);
                // da vi anvender et circle bibliotek, er der ikke nogle smart måde at få fat på det valgte ikon´s id
                // derfor bliver vi nød til at "hardcode" hvilke ikoner der skal slett
                circleLayout.removeViewAt(5);
                circleLayout.removeViewAt(4);
                circleLayout.removeViewAt(3);
                circleLayout.removeViewAt(1);
                circleLayout.removeViewAt(0);

                moveViewToScreenCenter(img_mad);

                break;
            case R.id.natur:
                // først kaldes metoden initialzeOnclik, som sætter de forskellige elementer synlige og starter deres animation.
                // hvis der er nogle tekster der skal sættes, bliver dette også gjort.
                ImageView img_natur = (ImageView) fgView.findViewById(R.id.natur);
                initializeOnClick(view);
                // da vi anvender et circle bibliotek, er der ikke nogle smart måde at få fat på det valgte ikon´s id
                // derfor bliver vi nød til at "hardcode" hvilke ikoner der skal slett
                circleLayout.removeViewAt(5);
                circleLayout.removeViewAt(4);
                circleLayout.removeViewAt(2);
                circleLayout.removeViewAt(1);
                circleLayout.removeViewAt(0);

                moveViewToScreenCenter(img_natur);

                break;
            case R.id.sport:
                ImageView img_sport = (ImageView) fgView.findViewById(R.id.sport);
                initializeOnClick(view);
                // da vi anvender et circle bibliotek, er der ikke nogle smart måde at få fat på det valgte ikon´s id
                // derfor bliver vi nød til at "hardcode" hvilke ikoner der skal slett
               circleLayout.removeViewAt(5);
               circleLayout.removeViewAt(3);
               circleLayout.removeViewAt(2);
               circleLayout.removeViewAt(1);
               circleLayout.removeViewAt(0);

                moveViewToScreenCenter(img_sport);

                break;

            case R.id.random:
                // først kaldes metoden initialzeOnclik, som sætter de forskellige elementer synlige og starter deres animation.
                // hvis der er nogle tekster der skal sættes, bliver dette også gjort.
                ImageView img_random = (ImageView) fgView.findViewById(R.id.random);
                initializeOnClick(view);
                // da vi anvender et circle bibliotek, er der ikke nogle smart måde at få fat på det valgte ikon´s id
                // derfor bliver vi nød til at "hardcode" hvilke ikoner der skal slett
               circleLayout.removeViewAt(4);
               circleLayout.removeViewAt(3);
               circleLayout.removeViewAt(2);
               circleLayout.removeViewAt(1);
               circleLayout.removeViewAt(0);

                moveViewToScreenCenter(img_random);

                break;
        }

    }



    @Override
    public void onRotationFinished(View view) {

        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(250);
        view.startAnimation(animation);

    }


    private void moveViewToScreenCenter(View view) {

        // sætter ikonets position til at være den nederst del af cirklen
        circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);

        // Denne metoden, finder ud af ikonets nuværende placering
        // Derefter finder den ud af hvor langt der er til center af skærmen fra den nuværende placering
        // Til slut tilføjer den så de manglende x og y værdier til ikonets nuværende x og y værdier.
        // herefter bliver der lavet en animation af ikonet, så det rykker sig stille op til center af skærmen
        RelativeLayout root = (RelativeLayout) fgView.findViewById(R.id.rootLayout);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        int xDest = dm.widthPixels / 2;
        xDest -= (view.getMeasuredWidth() / 2);
        int yDest = dm.heightPixels / 2 - (view.getMeasuredHeight() / 2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation(0, xDest - originalPos[0], 0, (yDest - originalPos[1]) - ((yDest - originalPos[1]) / 2));
        anim.setDuration(1000);
        anim.setFillAfter(true);
        view.startAnimation(anim);


    }

    // denne metoder kan tage en til mange seekbar ind, og rotere dem ved hjælp af en animation
    private void rotateSeekBar(SeekBar... s) {
        for (int i = 0; i < s.length; i++) {
            SeekBar SeekBarAraay = s[i];
            s[i].setDrawingCacheEnabled(true);
            Animation animation = new RotateAnimation(200, 360, s[i].getWidth(), s[i].getHeight());
            animation.setDuration(750);
            s[i].startAnimation(animation);
        }
    }

// denne metode kan tage en til mange textview ind, og rotere dem ved hjælp af en animation
    private void setTextViewRotate(TextView... t) {

        for (int i = 0; i < t.length; i++) {
            TextView textViewAraay = t[i];
            Animation animation = new RotateAnimation(200, 360, t[i].getWidth(), t[i].getHeight());
            animation.setDuration(750);
            t[i].startAnimation(animation);

        }
    }

    // når man har valgt hvilket genre man vil læse, skal der komme nogle andre elementer til syne
    // disse elemnter bliver gjort til syne i denne metode, plus ders telst også bliver sat til hvad den skal være
    // Der bliver også sat deres animationer
    // plus der bliver også lavet en animation til knappen LÆS!
    private void initializeOnClick(View v) {

        circleLayout.setEnabled(false);
        circleLayout.setRotating(false);
        rotateSeekBar(seekBarTime, seekBarDifficulty);

        readingTimeTxt.setText("Hvor lang tid vil du læse? (I minutter)");

        readingDifficultyTxt.setText("Hvad er dit niveau ?");

        selectedTextView.setText("");

        seekBarTimeValue.setText(String.valueOf(seekBarTime.getProgress()) + " Minutter");


        switch (seekBarDifficulty.getProgress()) {
            case 0:
                seekBarDifficultyValue.setText("Let øvet");
                break;
            case 1:
                seekBarDifficultyValue.setText("Øvet");
                break;
            case 2:
                seekBarDifficultyValue.setText("Meget øvet");
                break;
            case 3:
                seekBarDifficultyValue.setText("Ekspert");
                break;
            default:
                break;
        }
        seekBarTime.setVisibility(VISIBLE);
        seekBarDifficulty.setVisibility(VISIBLE);
        startTestButton.setVisibility(VISIBLE);
        startTestButton.setText("LÆS!");
        // animation for knappen
        Animation animation = new RotateAnimation(200, 360, startTestButton.getWidth(), startTestButton.getHeight());
        animation.setDuration(750);
        startTestButton.startAnimation(animation);

        setTextViewRotate(readingTimeTxt, readingDifficultyTxt, seekBarTimeValue, seekBarDifficultyValue);
    }


}
