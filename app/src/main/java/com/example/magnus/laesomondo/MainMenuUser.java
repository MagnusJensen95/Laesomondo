package com.example.magnus.laesomondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_user);
    }

    public void onMyProfile(View view){
        startActivity(new Intent(this, UserProfile.class));
    }

    public void onLogOut(View view){
        startActivity(new Intent(this, MainMenu.class));
    }
}