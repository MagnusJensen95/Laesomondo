package com.example.magnus.laesomondo;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadingTestPrerequisites extends AppCompatActivity {

    SeekBar seekBarTime;
    TextView seekBarTimeValue;

    SeekBar seekBarDifficulty;
    TextView seekBarDifficultyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_prerequisites);

        SeekBar seekBarTime = (SeekBar) findViewById(R.id.readingTestPrerequisitesTimeSlider);
        final TextView seekBarTimeValue = (TextView) findViewById(R.id.seekBarTimeValue);
        seekBarTimeValue.setText(String.valueOf(seekBarTime.getProgress()));

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarTimeValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        SeekBar seekBarDifficulty = (SeekBar) findViewById(R.id.readingTestPrerequisitesDifficultySlider);
        final TextView seekBarDifficultyValue = (TextView) findViewById(R.id.seekBarDifficultyValue);
        switch(seekBarDifficulty.getProgress()){
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
        seekBarDifficulty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                switch(progress){
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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void startReadingTest(View v) {

        //TODO:Below needs severe overhaul. More texts in strings resource, random or determined way
        //TODO:of loading differenting texts, title for textstring as well.
        String readingText = getString(R.string.readingTestReadingMaterial);

        Intent intent = new Intent(getBaseContext(), ReadingTest.class);
        intent.putExtra("timeStart", System.currentTimeMillis());
        intent.putExtra("readingTestType", "readingTestOriginal");
        intent.putExtra("TextToLoad", readingText);
        startActivity(intent);
    }
    //implement functionality, as well as cm start button (stop button in reading test).
}

