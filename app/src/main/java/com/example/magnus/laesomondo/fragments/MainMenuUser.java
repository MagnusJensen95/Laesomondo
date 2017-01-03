package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magnus.laesomondo.R;

public class MainMenuUser extends Fragment {

    private Button textFromNet, myProfile, logOut, readingTest;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_main_menu_user, container, false);

        textFromNet = (Button)view.findViewById(R.id.mainMenuUserLoggedInTextFromNet);
        myProfile  = (Button)view.findViewById(R.id.mainMenuUserLoggedInMyProfile);
        logOut  = (Button)view.findViewById(R.id.mainMenuUserLoggedInLogOut);
        readingTest = (Button)view.findViewById(R.id.mainMenuUserLoggedInReadingTestButton);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int buttonId = view.getId();
                switch (buttonId){
                    case R.id.mainMenuUserLoggedInTextFromNet:

                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                        R.animator.exit_to_right, R.animator.enter_from_right )
                                .replace(R.id.container_main,
                                        new TextFromNetAct()).addToBackStack(null).commit();

                        break;

                    case R.id.mainMenuUserLoggedInMyProfile:

                        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main,
                                new UserProfile()).addToBackStack(null).commit();

                        break;

                    case R.id.mainMenuUserLoggedInLogOut:

                        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main,
                                new MainMenu()).addToBackStack(null).commit();

                        break;

                    case R.id.mainMenuUserLoggedInReadingTestButton:

                        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main,
                                new ReadingTestPrerequisites()).addToBackStack(null).commit();

                        break;

                }
            }
        };

        textFromNet.setOnClickListener(listener);
        myProfile.setOnClickListener(listener);
        logOut.setOnClickListener(listener);
        readingTest.setOnClickListener(listener);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    return true;
                }
                return false;
            }
        });

        return view;
    }





}