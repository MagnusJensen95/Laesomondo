package com.example.magnus.laesomondo.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;

public class Settings extends AppCompatActivity {

    private TextView fontSize,fontValue;
    private SeekBar fontSizeSeekBar;
    private SharedPreferences prefs;
    private int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fontSize = (TextView) findViewById(R.id.eksempelTxt);
        fontValue = (TextView) findViewById(R.id.sizeValue);
        fontSizeSeekBar = (SeekBar) findViewById(R.id.seekBarFontSize);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int saveFontSize = prefs.getInt("textSize",size);
        Log.i("hejsa",""+saveFontSize);

        if (saveFontSize == 0){
            fontSizeSeekBar.setProgress(12);
            fontSize.setTextSize(12);
            fontValue.setText("skriftstørrelse : " + fontSizeSeekBar.getProgress());
        }
        else {
            fontSizeSeekBar.setProgress(saveFontSize);
          fontValue.setText("skriftstørrelse : " + fontSizeSeekBar.getProgress());
           fontSize.setTextSize(saveFontSize);
        }
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fontSize.setTextSize(progress);
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
    }
}
