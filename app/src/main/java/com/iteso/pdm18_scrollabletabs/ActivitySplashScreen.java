package com.iteso.pdm18_scrollabletabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.beans.User;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Intent intent;

        //If there is a user logged, continue to main activity
        if(getLoggedUserFromPreferences().isLogged()){
            intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
        } else {    // If there isn´t a user logged, continue to login activity
            intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 1300);

    }

    private User getLoggedUserFromPreferences(){
        //user data that will be returned
        User user = new User();

        //get from this app preferences, the stores user. Using default values
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_key), MODE_PRIVATE);
        user.setName(sharedPreferences.getString(
                getString(R.string.preference_user_name), ""));
        user.setPassword(sharedPreferences.getString(
                getString(R.string.preference_user_password), ""));
        user.setLogged(sharedPreferences.getBoolean(
                getString(R.string.preference_isLogged), false));

        //return user
        return user;
    }

}
