package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.activities.ReadingTest;

public class ReadingTestPrerequisites extends Fragment {

    SeekBar seekBarTime;
    TextView seekBarTimeValue;

    SeekBar seekBarDifficulty;
    TextView seekBarDifficultyValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.activity_reading_test_prerequisites, container, false);

        SeekBar seekBarTime = (SeekBar) view.findViewById(R.id.readingTestPrerequisitesTimeSlider);
        final TextView seekBarTimeValue = (TextView) view.findViewById(R.id.seekBarTimeValue);
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

        SeekBar seekBarDifficulty = (SeekBar) view.findViewById(R.id.readingTestPrerequisitesDifficultySlider);
        final TextView seekBarDifficultyValue = (TextView) view.findViewById(R.id.seekBarDifficultyValue);
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



        return view;
    }


    public void startReadingTest(View v) {

        //TODO:Below needs severe overhaul. More texts in strings resource, random or determined way
        //TODO:of loading differentiating texts, title for textstring as well.
        String readingText = getString(R.string.readingTestReadingMaterial);

        Intent intent = new Intent(getActivity().getApplicationContext(), ReadingTest.class);
        intent.putExtra("readingTestType", "readingTestOriginal");
        intent.putExtra("TextToLoad", readingText);
        intent.putExtra("TextTitle", "Default Titel");
        startActivity(intent);
    }
    //implement functionality, as well as cm start button (stop button in reading test).
}