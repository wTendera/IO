package com.example.io.io.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.io.io.R;
import com.example.io.io.database.TrainingsDataSource;
import com.example.io.io.database.UsersDataSource;
import com.example.io.io.models.Training;
import com.example.io.io.models.User;
import com.example.io.io.patterns.UserBuilder;

public class CreateTrainingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_training, menu);
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

    public void saveTraining(View view) {
        Training training = new Training(0, ((EditText) findViewById(R.id.name)).getText().toString());
        TrainingsDataSource dataSource = new TrainingsDataSource(this.getBaseContext());
        long id = dataSource.createOrUpdateTraining(training);
        CreateTrainingActivity.this.finish();
    }
}
