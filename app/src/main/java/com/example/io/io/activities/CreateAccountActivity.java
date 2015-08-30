package com.example.io.io.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.io.io.R;
import com.example.io.io.database.UsersDataSource;
import com.example.io.io.models.User;
import com.example.io.io.patterns.UserBuilder;


public class CreateAccountActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        if (checkUser()) {
            finishCreation();

        }
    }

    private void finishCreation() {
        Intent intent = new Intent(CreateAccountActivity.this, DrawerActivity.class);
        CreateAccountActivity.this.finish();
        startActivity(intent);
    }

    private boolean checkUser() {
        SharedPreferences prefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        return prefs.getLong("user_id", -1) != -1;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveUser(View view) {
        UserBuilder builder = new UserBuilder();
        builder.setNewFirstName(((EditText) findViewById(R.id.first_name)).getText().toString());
        builder.setNewLastName(((EditText) findViewById(R.id.last_name)).getText().toString());
        builder.setNewCity(((EditText) findViewById(R.id.city)).getText().toString());
        builder.setNewStreet(((EditText) findViewById(R.id.street)).getText().toString());
        builder.setNewZip(((EditText) findViewById(R.id.zip)).getText().toString());

        User u = builder.createPerson();
        UsersDataSource dataSource = new UsersDataSource(this.getBaseContext());
        long id = dataSource.createOrUpdateUser(u);
        SharedPreferences prefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        prefs.edit().putLong("user_id", id).apply();
        finishCreation();
    }
}
