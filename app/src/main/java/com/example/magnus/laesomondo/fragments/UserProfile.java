package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.dataclasses.DBHandler;
import com.example.magnus.laesomondo.dataclasses.Result;

import java.util.ArrayList;

public class UserProfile extends Fragment {

    TextView resultater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_user_profile, container, false);


        DBHandler db = new DBHandler(getActivity());

        resultater = (TextView) v.findViewById(R.id.grafTekst);

        ArrayList<Result> data = db.getContent();
        Log.i("DEBUG ARRAY LENGTH: ", ""+data.size());

        String titelsInDB = "";
        for (int i = 0; i < data.size(); i++){

            titelsInDB += (data.get(i).getTitel() + " | " +data.get(i).getTime() + " | " +data.get(i).getWords() + " | " +data.get(i).getLix() + " | " +data.get(i).getRatio());
            titelsInDB += "\n";

        }

        resultater.setText(titelsInDB);


        return v;
    }


}
