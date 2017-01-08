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

public class ReadingTestPrerequisites extends Fragment implements CircleLayout.OnItemSelectedListener,
        CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener {

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

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                seekBarTimeValue.setText(String.valueOf(seekBar.getProgress()) + " Minutter");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


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


        // set listeners
        circleLayout = (CircleLayout) fgView.findViewById(R.id.circle_layout);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnItemClickListener(this);
        circleLayout.setOnRotationFinishedListener(this);


        selectedTextView = (TextView) fgView.findViewById(R.id.selected_textView);

        String name = null;
        final View view = circleLayout.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }
        selectedTextView.setText(name);
        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = seekBarTime.getProgress();
                int difficulty = seekBarDifficulty.getProgress();
                String theme = ((CircleImageView) circleLayout.getSelectedItem()).getName();

                TextHandler handler = new TextHandler();

                String parseString = handler.retrieveText(getActivity(), time, diff, theme.toLowerCase() + ".txt");

                Bundle b = new Bundle();
                ReadingTest frag = new ReadingTest();

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

    @Override
    public void onItemClick(View view) {

        switch (view.getId()) {
            case R.id.historie:
                initializeOnClick(view);
                if (circleLayout.getChildCount() > 1) {

                    for (int i = 0; i < 5; i++) {
                        circleLayout.removeViewAt(circleLayout.getChildCount() - 1);
                    }
                }

                ImageView img_his = (ImageView) fgView.findViewById(R.id.historie);
                img_his.setLayoutParams((new ViewGroup.LayoutParams(300,300))); //ToDO Ved ikke om denne skal med :)
                moveViewToScreenCenter(img_his);

                break;
            case R.id.krimi:

                ImageView img_krimi = (ImageView) fgView.findViewById(R.id.krimi);
                initializeOnClick(view);

                circleLayout.removeViewAt(5);
                circleLayout.removeViewAt(4);
                circleLayout.removeViewAt(3);
                circleLayout.removeViewAt(2);
                circleLayout.removeViewAt(0);
                img_krimi.setLayoutParams((new ViewGroup.LayoutParams(300,300))); //ToDO Ved ikke om denne skal med :)
                moveViewToScreenCenter(img_krimi);

                break;
            case R.id.mad:

                ImageView img_mad = (ImageView) fgView.findViewById(R.id.mad);
                initializeOnClick(view);

                circleLayout.removeViewAt(5);
                circleLayout.removeViewAt(4);
                circleLayout.removeViewAt(3);
                circleLayout.removeViewAt(1);
                circleLayout.removeViewAt(0);
                img_mad.setLayoutParams((new ViewGroup.LayoutParams(300,300))); //ToDO Ved ikke om denne skal med :)
                moveViewToScreenCenter(img_mad);

                break;
            case R.id.natur:
                ImageView img_natur = (ImageView) fgView.findViewById(R.id.natur);
                initializeOnClick(view);

                circleLayout.removeViewAt(5);
                circleLayout.removeViewAt(4);
                circleLayout.removeViewAt(2);
                circleLayout.removeViewAt(1);
                circleLayout.removeViewAt(0);

                img_natur.setLayoutParams((new ViewGroup.LayoutParams(300,300))); //ToDO Ved ikke om denne skal med :)
                moveViewToScreenCenter(img_natur);

                break;
            case R.id.sport:
                ImageView img_sport = (ImageView) fgView.findViewById(R.id.sport);
                initializeOnClick(view);

               circleLayout.removeViewAt(5);
               circleLayout.removeViewAt(3);
               circleLayout.removeViewAt(2);
               circleLayout.removeViewAt(1);
               circleLayout.removeViewAt(0);

                img_sport.setLayoutParams((new ViewGroup.LayoutParams(300,300))); //ToDO Ved ikke om denne skal med :)
                moveViewToScreenCenter(img_sport);

                break;

            case R.id.random:
                ImageView img_random = (ImageView) fgView.findViewById(R.id.random);
                initializeOnClick(view);

               circleLayout.removeViewAt(4);
               circleLayout.removeViewAt(3);
               circleLayout.removeViewAt(2);
               circleLayout.removeViewAt(1);
               circleLayout.removeViewAt(0);

                img_random.setLayoutParams((new ViewGroup.LayoutParams(300,300))); //ToDO Ved ikke om denne skal med :)
                moveViewToScreenCenter(img_random);

                break;
        }

    }

    @Override
    public void onItemSelected(View view) {
        final String name;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        } else {
            name = null;
        }

        selectedTextView.setText(name);

        switch (view.getId()) {
            case R.id.historie:

                break;
            case R.id.krimi:

                break;
            case R.id.mad:

                break;
            case R.id.natur:

                break;
            case R.id.sport:

                break;
            case R.id.random:

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
        if (view instanceof TextView) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
            view.setLayoutParams(params);
        }
        circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);
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
        int x = xDest - originalPos[0];
        int y = yDest - (originalPos[1]) / 2;
        view.startAnimation(anim);


    }


    private void rotateSeekBar(SeekBar... s) {
        for (int i = 0; i < s.length; i++) {
            SeekBar SeekBarAraay = s[i];
            s[i].setDrawingCacheEnabled(true);
            Animation animation = new RotateAnimation(200, 360, s[i].getWidth(), s[i].getHeight());
            animation.setDuration(750);
            s[i].startAnimation(animation);
        }
    }


    private void setTextViewRotate(TextView... t) {

        for (int i = 0; i < t.length; i++) {
            TextView textViewAraay = t[i];
            Animation animation = new RotateAnimation(200, 360, t[i].getWidth(), t[i].getHeight());
            animation.setDuration(750);
            t[i].startAnimation(animation);

        }
    }

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
