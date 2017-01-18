package com.example.magnus.laesomondo.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;


public class AboutFrag extends Fragment {

    private TextView graphQuest, testQuest, webQuest;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View v = inflater.inflate(R.layout.fragment_about, container, false);

        return v;
    }

}
