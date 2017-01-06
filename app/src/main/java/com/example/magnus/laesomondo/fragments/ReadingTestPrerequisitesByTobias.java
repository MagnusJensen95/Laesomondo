package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.example.magnus.laesomondo.dataclasses.ResizeAnimation;
import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Tobias on 06-01-2017.
 */

public class ReadingTestPrerequisitesByTobias extends Fragment implements CircleLayout.OnItemSelectedListener,
        CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener {

    protected CircleLayout circleLayout;
    protected TextView selectedTextView;

    //private SeekBar seekBarTime;
    //TextView seekBarTimeValue;
    //SeekBar seekBarDifficulty;
    //TextView seekBarDifficultyValue;
    private SeekBar seekBar, seekBar2;
    private TextView textView, textView2, tidLaese, svaerhedsgrad;
    private Button las;
    private View fgView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fgView = inflater.inflate(R.layout.rtp, container, false);

        las = (Button) fgView.findViewById(R.id.btnStart);
        las.setVisibility(GONE);

        seekBar = (SeekBar) fgView.findViewById(R.id.seekBar1);
        seekBar.setVisibility(GONE);

        textView = (TextView) fgView.findViewById(R.id.textView1);

        seekBar2 = (SeekBar) fgView.findViewById(R.id.seekBar2);
        seekBar2.setVisibility(GONE);

        textView2 = (TextView) fgView.findViewById(R.id.textView2);

        tidLaese = (TextView) fgView.findViewById(R.id.TidLaese);

        svaerhedsgrad = (TextView) fgView.findViewById(R.id.TextSvaerhedsgrad);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()) + " Minutter");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                // TODO Auto-generated method stub
                switch (progresValue) {
                    case 0:
                        textView2.setText("Let øvet");
                        break;
                    case 1:
                        textView2.setText("Øvet");
                        break;
                    case 2:
                        textView2.setText("Meget øvet");
                        break;
                    case 3:
                        textView2.setText("Ekspert");
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
        View view = circleLayout.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }
        selectedTextView.setText(name);
        return fgView;
    }
        @Override
        public void onItemClick (View view){

            switch (view.getId()) {
                case R.id.historie:
                    if (circleLayout.getChildCount() > 1) {

                        for (int i = 0; i < 5; i++) {
                            circleLayout.removeViewAt(circleLayout.getChildCount() - 1);
                        }
                    }

                    ImageView img_his = (ImageView) fgView.findViewById(R.id.historie);
                    circleLayout.setEnabled(false);
                    circleLayout.setRotating(false);

                    moveViewToScreenCenter(img_his);
                   // moveViewToScreenCenter(selectedTextView);


                    rotateSeekBar(seekBar, seekBar2);

                    initializeOnClick(view);

                    setTextViewRotate(tidLaese, svaerhedsgrad, textView, textView2);

                    break;
                case R.id.krimi:
                    ImageView img_krimi = (ImageView) fgView.findViewById(R.id.krimi);

                    circleLayout.setEnabled(false);
                    circleLayout.setRotating(false);

                    circleLayout.removeViewAt(5);
                    circleLayout.removeViewAt(4);
                    circleLayout.removeViewAt(3);
                    circleLayout.removeViewAt(2);
                    circleLayout.removeViewAt(0);

                    circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);

                    moveViewToScreenCenter(img_krimi);

                    //moveViewToScreenCenter(selectedTextView);

                    rotateSeekBar(seekBar, seekBar2);

                    initializeOnClick(view);

                    setTextViewRotate(tidLaese, svaerhedsgrad, textView, textView2);


                    break;
                case R.id.mad:

                    ImageView img_mad = (ImageView) fgView.findViewById(R.id.mad);

                    circleLayout.setEnabled(false);
                    circleLayout.setRotating(false);

                    circleLayout.removeViewAt(5);
                    circleLayout.removeViewAt(4);
                    circleLayout.removeViewAt(3);
                    circleLayout.removeViewAt(1);
                    circleLayout.removeViewAt(0);

                    circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);

                    moveViewToScreenCenter(img_mad);

                   // moveViewToScreenCenter(selectedTextView);

                    rotateSeekBar(seekBar, seekBar2);

                    initializeOnClick(view);

                    setTextViewRotate(tidLaese, svaerhedsgrad, textView, textView2);
                    break;
                case R.id.natur:
                    ImageView img_natur = (ImageView) fgView.findViewById(R.id.natur);

                    circleLayout.setEnabled(false);
                    circleLayout.setRotating(false);

                    circleLayout.removeViewAt(5);
                    circleLayout.removeViewAt(4);
                    circleLayout.removeViewAt(2);
                    circleLayout.removeViewAt(1);
                    circleLayout.removeViewAt(0);

                    circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);

                    moveViewToScreenCenter(img_natur);

                   // moveViewToScreenCenter(selectedTextView);

                    rotateSeekBar(seekBar, seekBar2);

                    initializeOnClick(view);

                    setTextViewRotate(tidLaese, svaerhedsgrad, textView, textView2);
                    break;
                case R.id.sport:
                    ImageView img_sport = (ImageView) fgView.findViewById(R.id.sport);

                    circleLayout.setEnabled(false);
                    circleLayout.setRotating(false);

                    circleLayout.removeViewAt(5);
                    circleLayout.removeViewAt(3);
                    circleLayout.removeViewAt(2);
                    circleLayout.removeViewAt(1);
                    circleLayout.removeViewAt(0);

                    circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);

                    moveViewToScreenCenter(img_sport);

                    //moveViewToScreenCenter(selectedTextView);

                    rotateSeekBar(seekBar, seekBar2);

                    initializeOnClick(view);

                    setTextViewRotate(tidLaese, svaerhedsgrad, textView, textView2);
                    break;

                case R.id.random:
                    ImageView img_random = (ImageView) fgView.findViewById(R.id.random);

                    circleLayout.setEnabled(false);
                    circleLayout.setRotating(false);

                    circleLayout.removeViewAt(4);
                    circleLayout.removeViewAt(3);
                    circleLayout.removeViewAt(2);
                    circleLayout.removeViewAt(1);
                    circleLayout.removeViewAt(0);

                    circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);

                    moveViewToScreenCenter(img_random);

                   // moveViewToScreenCenter(selectedTextView);

                    rotateSeekBar(seekBar, seekBar2);

                    initializeOnClick(view);

                    setTextViewRotate(tidLaese, svaerhedsgrad, textView, textView2);
                    break;
            }

        }

        @Override
        public void onItemSelected (View view){
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
        public void onRotationFinished (View view){

            Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2);
            animation.setDuration(250);
            view.startAnimation(animation);

        }




    private void moveViewToScreenCenter( View view )
    {
        if(view instanceof TextView){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
            params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
            view.setLayoutParams(params);
        }
        circleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.SOUTH);
        RelativeLayout root = (RelativeLayout) fgView.findViewById( R.id.rootLayout);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );

        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);
        int yDest = dm.heightPixels/2 - (view.getMeasuredHeight()/2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , 0, (yDest - originalPos[1])-((yDest - originalPos[1])/2) );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        int x = xDest - originalPos[0];
        int y = yDest - (originalPos[1])/2;
        view.startAnimation(anim);



    }


    private void rotateSeekBar(SeekBar... s){
        for(int i = 0 ; i < s.length;i++) {
            SeekBar SeekBarAraay = s[i];
            s[i].setDrawingCacheEnabled(true);
            Animation animation = new RotateAnimation(200, 360, s[i].getWidth(), s[i].getHeight());
            animation.setDuration(1250);
            s[i].startAnimation(animation);
        }
    }


    private  void setTextViewRotate( TextView... t){

        for(int i = 0 ; i < t.length;i++) {
            TextView textViewAraay = t[i];
            Animation animation = new RotateAnimation(200, 360, t[i].getWidth(), t[i].getHeight());
            animation.setDuration(1250);
            t[i].startAnimation(animation);

        }
    }

    private  void initializeOnClick (View v){

        tidLaese.setText("Hvor lang tid vil du læse? (I minutter)");

        svaerhedsgrad.setText("Hvad er dit niveau ?");

        //selectedTextView.setText("Du har valgt : "+((CircleImageView) v).getName());
        selectedTextView.setText("");

        textView.setText(String.valueOf(seekBar.getProgress())+" Minutter");


        switch(seekBar2.getProgress()){
            case 0:
                textView2.setText("Let øvet");
                break;
            case 1:
                textView2.setText("Øvet");
                break;
            case 2:
                textView2.setText("Meget øvet");
                break;
            case 3:
                textView2.setText("Ekspert");
                break;
            default:
                break;
        }
        seekBar.setVisibility(VISIBLE);
        seekBar2.setVisibility(VISIBLE);
        las.setVisibility(VISIBLE);
        las.setText("LÆS!");
        // animation for knappen
        Animation animation = new RotateAnimation(200, 360, las.getWidth(), las.getHeight());
        animation.setDuration(1250);
        las.startAnimation(animation);
    }

}
