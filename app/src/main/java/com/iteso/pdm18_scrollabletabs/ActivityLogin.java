package com.iteso.pdm18_scrollabletabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        if (view.getId() == R.id.activity_login_button_login) {
            //get attributes of user to store in preferences
            String user_name = ((EditText) findViewById(R.id.activity_login_name)).getText().toString();
            String user_password = ((EditText) findViewById(R.id.activity_login_password)).getText().toString();

            //set logged user to preferences
            setLoggedUserToPreferences(user_name, user_password);
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            startActivity(intent);
            finish();
        }
    }

    private void setLoggedUserToPreferences(String user_name, String user_password) {

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.preference_user_name), user_name);
        editor.putString(getString(R.string.preference_user_password), user_password);
        editor.putBoolean(getString(R.string.preference_isLogged), true);
        editor.commit();
    }

}
