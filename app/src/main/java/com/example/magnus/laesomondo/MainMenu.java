package com.example.magnus.laesomondo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v =  inflater.inflate(R.layout.activity_main_menu, container, false);

        return v;
    }



    public void onLaeseTest(View view){
        //Intent intent = new Intent(this, ReadingTestPrerequisites.class);

        //startActivity(intent);


    }

    public void onLogInd(View view){
        //startActivity(new Intent(this, LogIn.class));
    }

    public void onWebContent(View view){

        //startActivity(new Intent(this, TextFromNetAct.class));
    }

}
