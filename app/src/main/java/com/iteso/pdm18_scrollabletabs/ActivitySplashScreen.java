package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.beans.User;
import com.iteso.pdm18_scrollabletabs.tools.DatabaseHandler;
import com.iteso.pdm18_scrollabletabs.tools.StoreControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Intent intent;

        //If there is a user logged, continue to main activity
        if (getLoggedUserFromPreferences().isLogged()) {
            intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
        } else {    // If there isn´t a user logged, continue to login activity
            intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
        }


        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this);
        StoreControl storeControl = new StoreControl();
        ArrayList<Store> stores = storeControl.getStores(databaseHandler);

        if (stores.size() == 0) {
            storeControl.addStore(new Store(
                    1, "BestBuy", "01 800 237 8289", 0, 1.0, 2.0,
                    new City(1, "Guadalajara")), databaseHandler);

            storeControl.addStore(new Store(
                    2, "ZaraHome", " 01 81 8378 0814", 1, 3.0, 4.0,
                    new City(2, "Monterrey")), databaseHandler);

            storeControl.addStore(new Store(
                    3, "Steren", "01 55 5545 5947", 2, 5.0, 6.0,
                    new City(3, "Ciudad de México")), databaseHandler);
        }

        startActivity(intent);
        finish();
    }

    private User getLoggedUserFromPreferences() {
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
