package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;

public class ReadingTestPrerequisites extends Fragment {

    SeekBar seekBarTime;
    TextView seekBarTimeValue;
    private Button startTestButton;

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


        startTestButton = (Button) view.findViewById(R.id.readingTestPrerequisitesStartButton);
        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String readingText = getString(R.string.readingTestReadingMaterial);

                Bundle b = new Bundle();
                ReadingTest frag = new ReadingTest();

                b.putString("readingTestType", "readingTestOriginal");
                b.putString("TextToLoad", readingText);
                b.putString("TextTitle", "Default Titel");
                frag.setArguments(b);

                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                        R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main,
                       frag).addToBackStack(null).commit();
            }
        });

        return view;
    }



}