package org.lewisandclark.csd.finalproject;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.lewisandclark.csd.finalproject.dialogs.ExitAlertDialog;
import org.lewisandclark.csd.finalproject.utils.SoundHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String NAV_TAG = "Navigation";
    private SoundHelper mSoundHelper;
    private TextView mOpeningScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mOpeningScreen = findViewById(R.id.opening_screen);

        //Initialize sound helper class that wraps SoundPool for audio effects
        mSoundHelper = new SoundHelper(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment contentFragment = null;

        if (id == R.id.nav_dog) {
            //opens up dog pictures window, plays dog_bark.wav, makes opening text vanish
            Log.i(NAV_TAG, "Dog Pictures Pressed");
            mOpeningScreen.setVisibility(View.INVISIBLE);
            contentFragment = new DogPicturesActivity();
            mSoundHelper.playDogSound();
        } else if (id == R.id.nav_cat) {
            //opens up cat pictures widow, plays cat_meow.wav, makes opening text vanish
            Log.i(NAV_TAG, "Cat Pictures Pressed");
            mOpeningScreen.setVisibility(View.INVISIBLE);
            contentFragment = new CatPicturesActivity();
           mSoundHelper.playCatSound();

        } else if (id == R.id.nav_exit) {
            //opens up dialog alert to ask user if they want to exit the app
            ExitAlertDialog exitDialog = ExitAlertDialog.newInstance(null, null);
            exitDialog.show(getSupportFragmentManager(), null);
        }

        if (contentFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, contentFragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
