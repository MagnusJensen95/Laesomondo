package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.dataclasses.Timer;


public class MainMenu extends Fragment {

    private Button logIn, textFromNetButton, laeseTestButton;


    public int counter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v =  inflater.inflate(R.layout.fragment_main_menu, container, false);

        // en counter til at tælle hvor mange gange bruger har klikket på tilabge knappen,
        // anvendes til at hvis bruger klikker to gange tilbage skal appen lukke ned
    counter = 0;

        logIn = (Button)v.findViewById(R.id.mainMenuLogInButton);

        textFromNetButton = (Button)v.findViewById(R.id.mainMenuWebcontentButton);
        laeseTestButton  = (Button)v.findViewById(R.id.mainMenuUserLoggedInReadingTestButton);

        //Sætter clicklistner på de forskellige knapper vi har i vores view

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int buttonId = view.getId();
                switch (buttonId){
                    case R.id.mainMenuLogInButton:
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                        R.animator.exit_to_right, R.animator.enter_from_right )
                                .replace(R.id.container_main,
                                new LogIn()).addToBackStack(null).commit();

                        break;
                    case R.id.mainMenuWebcontentButton:

                        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main,
                                new TextFromNetAct()).addToBackStack(null).commit();


                        break;
                    case R.id.mainMenuUserLoggedInReadingTestButton:

                        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main,
                                new ReadingTestPrerequisites()).addToBackStack(null).commit();

                        break;

                }
            }
        };



        logIn.setOnClickListener(listener);
        textFromNetButton.setOnClickListener(listener);
        laeseTestButton.setOnClickListener(listener);

        // Sætter en keylistiner på viewet. Hvis man klikker 2 gange på tilbage knappen, så afslutter man appen.

        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    counter++;
                    if(counter == 1){
                        Toast.makeText(getActivity(), "Klik tilbage igen for at afslutte", Toast.LENGTH_SHORT).show();
                    }
                    if(counter == 4){
                        getActivity().finish();
                    }

                    return true;
                }
                return false;
            }
        });



        return v;
    }




}
