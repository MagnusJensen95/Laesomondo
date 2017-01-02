package com.example.magnus.laesomondo;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

            titelsInDB += data.get(i).getTitel();
            titelsInDB += "\n";

        }

        resultater.setText(titelsInDB);


        return v;
    }


}
