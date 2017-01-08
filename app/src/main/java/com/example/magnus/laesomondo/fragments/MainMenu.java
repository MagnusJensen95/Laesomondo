package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magnus.laesomondo.R;

public class MainMenu extends Fragment {

    private Button logIn, textFromNetButton, laeseTestButton;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v =  inflater.inflate(R.layout.activity_main_menu, container, false);

        logIn = (Button)v.findViewById(R.id.mainMenuLogInButton);

        textFromNetButton = (Button)v.findViewById(R.id.mainMenuWebcontentButton);
        laeseTestButton  = (Button)v.findViewById(R.id.mainMenuUserLoggedInReadingTestButton);

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

        return v;
    }




}
