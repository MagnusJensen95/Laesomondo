package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magnus.laesomondo.R;

public class LogIn extends Fragment {

    private Button logIn;

//123
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        logIn = (Button)view.findViewById(R.id.btn_login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                        R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main, new MainMenuUser()).addToBackStack(null).commit();
            }
        });

        return view;
    }






}
