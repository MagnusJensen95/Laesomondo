package com.example.magnus.laesomondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    public void onLaeseTest(View view){
        Intent intent = new Intent(this, ReadingTestPrerequisites.class);
        startActivity(intent);
    }

    public void onLogInd(View view){
        startActivity(new Intent(this, LogIn.class));
    }

}
