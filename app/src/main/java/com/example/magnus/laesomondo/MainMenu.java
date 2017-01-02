package com.example.magnus.laesomondo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean emulator = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if(!emulator){

            Fabric.with(this, new Crashlytics());
           // throw new RuntimeException("This is a testcrash");
        }


        setContentView(R.layout.activity_main_menu);

    }

    public void onLaeseTest(View view){
        Intent intent = new Intent(this, ReadingTestPrerequisites.class);

        startActivity(intent);


    }

    public void onLogInd(View view){
        startActivity(new Intent(this, LogIn.class));
    }

    public void onWebContent(View view){
        startActivity(new Intent(this, TextFromNetAct.class));
    }

}
