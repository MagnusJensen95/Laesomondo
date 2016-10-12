package com.example.magnus.laesomondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    TextView resultater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        DBHandler db = new DBHandler(this);

        resultater = (TextView) findViewById(R.id.grafTekst);

        ArrayList<Result> data = db.getContent();
        Log.i("DEBUG ARRAY LENGTH: ", ""+data.size());

        String titelsInDB = "";
        for (int i = 0; i < data.size(); i++){

            titelsInDB += data.get(i).getTitel();
            titelsInDB += "\n";

        }

        resultater.setText(titelsInDB);

    }
}
