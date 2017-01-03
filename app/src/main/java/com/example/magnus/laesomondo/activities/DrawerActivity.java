package com.example.magnus.laesomondo.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.example.magnus.laesomondo.fragments.MainMenu;
import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.fragments.ReadingTestPrerequisites;
import com.example.magnus.laesomondo.fragments.TextFromNetAct;
import com.example.magnus.laesomondo.fragments.UserProfile;

import io.fabric.sdk.android.Fabric;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        boolean emulator = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if(!emulator){

            Fabric.with(this, new Crashlytics());
            // throw new RuntimeException("This is a testcrash");
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        manager = getFragmentManager();

       manager.beginTransaction().replace(R.id.container_main, new MainMenu()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
                  }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.myProfileDrawer) {
            manager.beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                    R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main, new UserProfile()).addToBackStack("").commit();
        } else if (id == R.id.readingTestDrawer) {
            manager.beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                    R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main, new ReadingTestPrerequisites()).addToBackStack("").commit();

        } else if (id == R.id.textFromNetDrawer) {

            manager.beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                    R.animator.exit_to_right, R.animator.enter_from_right ).replace(R.id.container_main, new TextFromNetAct()).addToBackStack("").commit();

        } else if (id == R.id.aboutDrawer) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
