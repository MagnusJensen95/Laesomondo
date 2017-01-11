package com.example.magnus.laesomondo.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.kizitonwose.colorpreference.ColorDialog;
import com.kizitonwose.colorpreference.ColorShape;

public class Settings extends AppCompatActivity implements ColorDialog.OnColorSelectedListener {

    private TextView exampleTextView,fontValue;
    private SeekBar fontSizeSeekBar;
    private SharedPreferences prefs;
    private int fontSize,fontColor,backgroundColor;

    private Button btnBackColor,btnFontColor,btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        exampleTextView = (TextView) findViewById(R.id.eksempelTxt);
        fontValue = (TextView) findViewById(R.id.sizeValue);
        fontSizeSeekBar = (SeekBar) findViewById(R.id.seekBarFontSize);
        btnBackColor = (Button) findViewById(R.id.btnBackgroundColor);
        btnFontColor = (Button) findViewById(R.id.btnFontColor);
        btnReset = (Button) findViewById(R.id.btnReset);
        
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int saveFontSize = prefs.getInt("textSize", fontSize);
        int saveFontColor = prefs.getInt("fontColor",fontColor);
        int saveBackgroundColor = prefs.getInt("backgroundColor",backgroundColor);
        
        if (saveFontSize == 0){
            fontSizeSeekBar.setProgress(12);
            exampleTextView.setTextSize(12);
            fontValue.setText("skriftstørrelse : " + fontSizeSeekBar.getProgress());
        }
        else {
            fontSizeSeekBar.setProgress(saveFontSize);
          fontValue.setText("skriftstørrelse : " + fontSizeSeekBar.getProgress());
           exampleTextView.setTextSize(saveFontSize);
        }

        if(saveFontColor == 0){

        }
        else{
            exampleTextView.setTextColor(saveFontColor);
        }
        if(saveBackgroundColor == 0){

        }
        else{
            exampleTextView.setBackgroundColor(saveBackgroundColor);
        }


        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                exampleTextView.setTextSize(progress);
                fontValue.setText("skriftstørrelse : "+ fontSizeSeekBar.getProgress());

                prefs.edit().putInt("textSize",progress).commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnBackColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorDialog.Builder(Settings.this)
                        .setColorShape(ColorShape.CIRCLE) //CIRCLE or SQUARE
                        .setColorChoices(R.array.color_choices) //an array of colors
                        .setSelectedColor(Color.GREEN) //the checked color
                        .setTag("TAG") // tags can be useful when multiple components use the picker within an activity
                        .show();
            }
        });

        btnFontColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorDialog.Builder(Settings.this)
                        .setColorShape(ColorShape.CIRCLE) //CIRCLE or SQUARE
                        .setColorChoices(R.array.color_choices) //an array of colors
                        .setSelectedColor(Color.GREEN) //the checked color
                        .setTag("font") // tags can be useful when multiple components use the picker within an activity
                        .show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exampleTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                exampleTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                exampleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                prefs.edit().putInt("fontColor",getResources().getColor(R.color.colorPrimaryDark)).commit();
                prefs.edit().putInt("backgroundColor",getResources().getColor(R.color.colorPrimary)).commit();
                prefs.edit().putInt("textSize",16).commit();
            }
        });
    }

    @Override
    public void onColorSelected(int newColor, String tag) {
        if(tag.equals("font")){
            exampleTextView.setTextColor(newColor);
            prefs.edit().putInt("fontColor",newColor).commit();
        }
        else {
            exampleTextView.setBackgroundColor(newColor);
            prefs.edit().putInt("backgroundColor",newColor).commit();
        }
    }
}
